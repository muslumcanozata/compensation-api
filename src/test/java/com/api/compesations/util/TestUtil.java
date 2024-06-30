package com.api.compesations.util;

import com.api.compesations.domain.dto.CompensationDTO;
import com.api.compesations.domain.entity.Compensation;

public class TestUtil {
    public static Compensation createCompensation() {
        Compensation compensation = new Compensation();
        compensation.setId(1L);
        compensation.setAge("25");
        compensation.setIndustry("IT");
        compensation.setJobTitle("Software Engineer");
        compensation.setAnnualSalary("100000");
        compensation.setCurrency("USD");
        compensation.setLocation("New York");
        compensation.setYearsExperience("5");
        compensation.setJobTitleContext("Java");
        compensation.setOtherCurrency("EUR");
        return compensation;
    }

    public static CompensationDTO createCompensationDTO() {
        Compensation compensation = createCompensation();
        return CompensationDTO.fromEntity(compensation);
    }
}
