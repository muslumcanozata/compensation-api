package com.api.compesations.service;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.domain.entity.Compensation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CompensationService {
    List<CompensationDTO> getAllCompensations();
    CompensationDTO getCompensationById(Long id);
    List<CompensationDTO> getCompensationsByFilter(Map<String, String> filters);
    List<CompensationDTO> getCompensationsSorted(String sort, String order, Integer pageNo, Integer pageSize);
    List<CompensationDTO> uploadCsv(MultipartFile file);
    Long createCompensation(Compensation compensation);
}