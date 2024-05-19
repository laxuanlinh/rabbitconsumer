package com.example.rabbitconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"${rabbitmq.queue.audit}"})
    public void onAudit(String message) throws JsonProcessingException {
        AuditRequest auditRequest = objectMapper.readValue(message, AuditRequest.class);
        log.info("Received audit request {}", auditRequest);
    }

}
