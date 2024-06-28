package com.api.compesations.service;

import com.api.compesations.domain.dto.CompensationDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CompensationService {
    List<CompensationDTO> getAllCompensations();

    CompensationDTO getCompensationById(Long id);

    List<CompensationDTO> getCompensationsByFilter(String zipCode, BigDecimal salary);

    List<CompensationDTO> getCompensationsSorted(String sortBy);
}
