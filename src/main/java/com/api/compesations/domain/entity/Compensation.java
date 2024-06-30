package com.api.compesations.domain.entity;

import com.api.compesations.constants.TableNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = TableNames.COMPENSATION)
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private int age;
    private String industry;
    private String jobTitle;
    private BigDecimal annualSalary;
    private String currency;
    private String location;
    private int yearsExperience;
    private String jobTitleContext;
    private String otherCurrency;
}