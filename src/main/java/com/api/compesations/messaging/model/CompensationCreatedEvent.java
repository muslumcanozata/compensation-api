package com.api.compesations.messaging.model;

import com.api.compesations.domain.entity.Compensation;

public record CompensationCreatedEvent(
    Compensation compensation
) {}
