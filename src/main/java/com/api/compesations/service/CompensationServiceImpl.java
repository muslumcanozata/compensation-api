package com.api.compesations.service;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.domain.entity.Compensation;
import com.api.compesations.exception.CompensationNotFoundException;
import com.api.compesations.repository.CompensationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompensationServiceImpl implements CompensationService{
    private final CompensationRepository compensationRepository;

    public List<CompensationDTO> getAllCompensations() {
        List<Compensation> compensations = compensationRepository.findAll();
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }

    public CompensationDTO getCompensationById(Long id) {
        Optional<Compensation> compensation = compensationRepository.findById(id);
        if (compensation.isPresent()) {
            return CompensationDTO.fromEntity(compensation.get());
        }
        throw new CompensationNotFoundException(id);
    }

    public List<CompensationDTO> getCompensationsByFilter(String zipCode, BigDecimal salary) {
        List<Compensation> compensations = compensationRepository.findAll();
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }

    public List<CompensationDTO> getCompensationsSorted(String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        List<Compensation> compensations = compensationRepository.findAll(sort);
        return compensations.stream().map(CompensationDTO::fromEntity).toList();
    }
}