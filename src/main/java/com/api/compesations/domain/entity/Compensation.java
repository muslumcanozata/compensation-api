package com.api.compesations.domain.entity;

import com.api.compesations.constants.TableNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableNames.COMPENSATION)
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp;
    private String location;
    private String gender;
    private String race;
    private String employer;
    private String title;
    private Integer yearsOfExperience;
    private Integer yearsAtEmployer;
    private Double baseSalary;
    private Double bonus;
    private Double stockOptions;
    private String signOnBonus;
    private String annualBonus;
    private String annualStockValue;
    private String yearOfData;
}