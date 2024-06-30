package com.api.compesations.service;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.domain.entity.Compensation;
import com.api.compesations.exception.CompensationNotFoundException;
import com.api.compesations.messaging.publisher.CompensationCreatedPublisher;
import com.api.compesations.repository.CompensationRepository;
import com.api.compesations.util.CsvUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompensationServiceImpl implements CompensationService{
    private final CompensationRepository compensationRepository;
    private final CompensationCreatedPublisher compensationCreatedPublisher;
    @Override
    public List<CompensationDTO> getAllCompensations() {
        List<Compensation> compensations = compensationRepository.findAll();
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }

    @Override
    public CompensationDTO getCompensationById(Long id) {
        Optional<Compensation> compensation = compensationRepository.findById(id);
        if (compensation.isPresent()) {
            return CompensationDTO.fromEntity(compensation.get());
        }
        throw new CompensationNotFoundException(id);
    }

    @Override
    public List<CompensationDTO> getCompensationsByFilter(Map<String, String> filters) {
        Specification<Compensation> specification = CompensationSpecification.getSpecifications(filters);
        List<Compensation> compensations = compensationRepository.findAll(specification);
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }

    @Override
    public List<CompensationDTO> getCompensationsSorted(String sortBy, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        List<Compensation> compensations = compensationRepository.findAll(sort);
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }


    @Override
    public List<CompensationDTO> uploadCsv(MultipartFile file) {
        if (CsvUtil.hasCsvFormat(file)) {
            try {
                List<Compensation> compensations = CsvUtil.csvToCompensationList(file.getInputStream());
                compensations.forEach(compensationCreatedPublisher::publishCompensationCreatedEvent);
                return compensations.stream().map(CompensationDTO::fromEntity).toList();
            } catch (IOException e) {
                throw new RuntimeException("fail to store csv data: " + e.getMessage());
            }
        }
        throw new RuntimeException("Please upload a csv file!");
    }
}