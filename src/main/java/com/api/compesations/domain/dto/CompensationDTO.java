package com.api.compesations.domain.dto;

import com.api.compesations.domain.entity.Compensation;

public record CompensationDTO(
    Long id,
    String timestamp,
    String location,
    String gender,
    String race,
    String employer,
    String title,
    Integer yearsOfExperience,
    Integer yearsAtEmployer,
    Double baseSalary,
    Double bonus,
    Double stockOptions,
    String signOnBonus,
    String annualBonus,
    String annualStockValue,
    String yearOfData) {

        public static CompensationDTO fromEntity(Compensation compensation) {
            return new CompensationDTO(
                compensation.getId(),
                compensation.getTimestamp(),
                compensation.getLocation(),
                compensation.getGender(),
                compensation.getRace(),
                compensation.getEmployer(),
                compensation.getTitle(),
                compensation.getYearsOfExperience(),
                compensation.getYearsAtEmployer(),
                compensation.getBaseSalary(),
                compensation.getBonus(),
                compensation.getStockOptions(),
                compensation.getSignOnBonus(),
                compensation.getAnnualBonus(),
                compensation.getAnnualStockValue(),
                compensation.getYearOfData()
            );
        }
}
