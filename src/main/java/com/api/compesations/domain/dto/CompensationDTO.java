package com.api.compesations.domain.dto;

import com.api.compesations.domain.entity.Compensation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompensationDTO(
    Long id,
    LocalDateTime timestamp,
    Integer age,
    String industry,
    String jobTitle,
    BigDecimal annualSalary,
    String currency,
    String location,
    Integer yearsExperience,
    String jobTitleContext,
    String otherCurrency) {

        public static CompensationDTO fromEntity(Compensation compensation) {
            return new CompensationDTO(
                compensation.getId(),
                compensation.getTimestamp(),
                compensation.getAge(),
                compensation.getIndustry(),
                compensation.getJobTitle(),
                compensation.getAnnualSalary(),
                compensation.getCurrency(),
                compensation.getLocation(),
                compensation.getYearsExperience(),
                compensation.getJobTitleContext(),
                compensation.getOtherCurrency()
            );
        }
}
