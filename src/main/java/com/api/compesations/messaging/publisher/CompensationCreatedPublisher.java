package com.api.compesations.messaging.publisher;

import com.api.compesations.domain.entity.Compensation;
import com.api.compesations.messaging.model.CompensationCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompensationCreatedPublisher {
    private final RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.compensations.created.exchange}")
    private String exchange;

    @Value("${rabbitmq.compensations.created.routingKey}")
    private String routingKey;

    public void publishCompensationCreatedEvent(Compensation compensation) {
        CompensationCreatedEvent event = new CompensationCreatedEvent(compensation);
        log.info("Publishing compensation created event to the queue: {}", event);
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}