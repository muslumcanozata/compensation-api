package com.api.compesations.messaging.listener;


import com.api.compesations.messaging.model.CompensationCreatedEvent;
import com.api.compesations.service.CompensationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CompensationCreatedListener {
    private final CompensationService service;


    @RabbitListener(queues = "${rabbitmq.compensations.created.queue}", ackMode = "AUTO")
    public void processCompensationCreatedEvent(CompensationCreatedEvent event) {
        try {
            Long id = service.createCompensation(event.compensation());
            log.info("Compensation created event processed successfully: {}", id);
        } catch (Exception e) {
            log.error("Failed to process add member into mail group event: {}", e.getMessage());
        }
    }
}