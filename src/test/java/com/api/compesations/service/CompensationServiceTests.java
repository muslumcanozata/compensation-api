package com.api.compesations.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.domain.entity.Compensation;
import com.api.compesations.exception.CompensationNotFoundException;
import com.api.compesations.messaging.publisher.CompensationCreatedPublisher;
import com.api.compesations.repository.CompensationRepository;
import com.api.compesations.util.CsvUtil;
import com.api.compesations.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompensationServiceTests {

    @Mock
    private CompensationRepository compensationRepository;

    @Mock
    private CompensationCreatedPublisher compensationCreatedPublisher;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    private Compensation compensation;
    private CompensationDTO compensationDTO;

    @BeforeEach
    void setUp() {
        compensation = TestUtil.createCompensation();
        compensationDTO = TestUtil.createCompensationDTO();
    }

    @Test
    void getAllCompensations_whenFoundAnyData_ThenReturnList() {
        List<Compensation> compensations = Collections.singletonList(compensation);
        when(compensationRepository.findAll()).thenReturn(compensations);

        List<CompensationDTO> result = compensationService.getAllCompensations();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(compensationDTO, result.get(0));
    }

    @Test
    void getAllCompensations_whenNotFound_ThenReturnEmptyList() {
        when(compensationRepository.findAll()).thenReturn(Collections.emptyList());

        List<CompensationDTO> result = compensationService.getAllCompensations();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCompensationById_whenFound_ThenReturnDTO() {
        when(compensationRepository.findById(1L)).thenReturn(Optional.of(compensation));

        CompensationDTO result = compensationService.getCompensationById(1L);

        assertNotNull(result);
        assertEquals(compensationDTO, result);
    }

    @Test
    void getCompensationById_whenNotFound_ThenThrowCompensationNotFoundException() {
        when(compensationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CompensationNotFoundException.class, () -> compensationService.getCompensationById(1L));
    }

    @Test
    void getCompensationsByFilter_whenFoundAnyData_thenReturnList() {
        Map<String, String> filters = new HashMap<>();
        filters.put("annualSalary[gte]", "120000");

        List<Compensation> compensations = Collections.singletonList(compensation);
        when(compensationRepository.findAll(any(Specification.class))).thenReturn(compensations);

        List<CompensationDTO> result = compensationService.getCompensationsByFilter(filters);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(compensationDTO, result.get(0));
    }

    @Test
    void getCompensationsByFilter_WhenNotFoundAnyData_ThenReturnEmptyList() {
        Map<String, String> filters = new HashMap<>();
        filters.put("annualSalary[gte]", "120000");

        when(compensationRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());

        List<CompensationDTO> result = compensationService.getCompensationsByFilter(filters);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void uploadCsv_whenDatasetIsInExpectedFormat_thenReturnQueuedData() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream("".getBytes()));
        when(file.getOriginalFilename()).thenReturn("compensations.csv");

        try (MockedStatic<CsvUtil> mockedCsvUtil = mockStatic(CsvUtil.class)) {
            mockedCsvUtil.when(() -> CsvUtil.hasCsvFormat(file)).thenReturn(true);
            mockedCsvUtil.when(() -> CsvUtil.csvToCompensationList(any(InputStream.class))).thenReturn(Collections.singletonList(compensation));

            List<CompensationDTO> result = compensationService.uploadCsv(file);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(compensationDTO, result.get(0));
        }
    }

    @Test
    void uploadCsv_whenDatasetIsNotInExpectedFormat_thenThrowRuntimeException() {
        MultipartFile file = mock(MultipartFile.class);

        try (MockedStatic<CsvUtil> mockedCsvUtil = mockStatic(CsvUtil.class)) {
            mockedCsvUtil.when(() -> CsvUtil.hasCsvFormat(file)).thenReturn(false);

            assertThrows(RuntimeException.class, () -> compensationService.uploadCsv(file));
        }
    }

    @Test
    void createCompensation_whenThereIsNoException_ThenReturnId() {
        when(compensationRepository.save(any(Compensation.class))).thenReturn(compensation);

        Long result = compensationService.createCompensation(compensation);

        assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    void createCompensation_whenThereIsAnException_ThenThrowRuntimeException() {
        when(compensationRepository.save(any(Compensation.class))).thenThrow(new RuntimeException("Save failed"));

        assertThrows(RuntimeException.class, () -> compensationService.createCompensation(compensation));
    }

}

