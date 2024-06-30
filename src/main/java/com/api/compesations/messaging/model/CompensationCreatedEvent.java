package com.api.compesations.messaging.model;

public record CompensationCreatedEvent(
    Long id,
    Long employeeId,
    String employeeName,
    String employeeRole,
    String employeeDepartment,
    String employeeManager,
    String employeeLocation,
    String employeeJoiningDate,
    String employeeSalary,
    String employeeCompensationType,
    String employeeCompensationAmount,
    String employeeCompensationReason,
    String employeeCompensationStatus
) {}
