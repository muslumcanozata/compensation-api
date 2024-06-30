package com.api.compesations.controller;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.exception.UnsupportedFileFormatException;
import com.api.compesations.service.CompensationService;
import com.api.compesations.util.CsvUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/batch-operations")
public class BatchOperationController {
    private final CompensationService service;

    @PostMapping(name="/csv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<CompensationDTO>> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !Objects.equals(file.getContentType(), CsvUtil.TYPE)) {
            throw new UnsupportedFileFormatException("Only CSV files are supported and cannot be null.");
        }
        return ResponseEntity.ok(service.uploadCsv(file));
    }
}
