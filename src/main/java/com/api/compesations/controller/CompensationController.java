package com.api.compesations.controller;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public ResponseEntity<List<CompensationDTO>> getCompensationsByFilter(@RequestParam(required = false) String zipCode,
                                                          @RequestParam(required = false) BigDecimal salary) {
        return ResponseEntity.ok(compensationService.getCompensationsByFilter(zipCode, salary));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<CompensationDTO>> getCompensationsSorted(@RequestParam String sortBy) {
        return ResponseEntity.ok(compensationService.getCompensationsSorted(sortBy));
    }
}
