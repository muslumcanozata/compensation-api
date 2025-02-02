package com.api.compesations.domain.entity;

import com.api.compesations.constants.TableNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String age;
    private String industry;
    private String jobTitle;
    private String annualSalary;
    private String currency;
    private String location;
    private String yearsExperience;
    private String jobTitleContext;
    private String otherCurrency;
}