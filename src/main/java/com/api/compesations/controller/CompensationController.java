package com.api.compesations.controller;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.service.CompensationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/compensations")
public class CompensationController {
    private final CompensationService compensationService;

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
    public ResponseEntity<List<CompensationDTO>> getCompensationsSorted(@RequestParam String sortBy, @RequestParam String order, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(compensationService.getCompensationsSorted(sortBy, order, pageNo, pageSize));
    }
}
