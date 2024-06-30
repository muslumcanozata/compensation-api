package com.api.compesations.controller;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compensations")
public class CompensationController {
    @Autowired
    private CompensationService compensationService;

    @GetMapping
    public ResponseEntity<List<CompensationDTO>> getAllCompensations() {
        return ResponseEntity.ok(compensationService.getAllCompensations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompensationDTO> getCompensationById(@PathVariable Long id) {
        return ResponseEntity.ok(compensationService.getCompensationById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CompensationDTO>> getCompensationsByFilter(@RequestParam Map<String, String> filters) {
        return ResponseEntity.ok(compensationService.getCompensationsByFilter(filters));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<CompensationDTO>> getCompensationsSorted(@RequestParam String sortBy, @RequestParam String order) {
        return ResponseEntity.ok(compensationService.getCompensationsSorted(sortBy, order));
    }

    @PostMapping("/csv")
    public ResponseEntity<List<CompensationDTO>> uploadCsv(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(compensationService.uploadCsv(file));
    }
}
