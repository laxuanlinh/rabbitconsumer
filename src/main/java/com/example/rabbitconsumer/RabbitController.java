package com.example.rabbitconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class RabbitController {

    @Value("${rabbitmq.queue.audit}")
    private String auditQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/api/audit")
    public ResponseEntity<HttpStatus> postAudit(@RequestBody AuditRequest auditRequest) throws JsonProcessingException {
        auditRequest.setTime(LocalDateTime.now());
        log.info("Sending audit message {} to queue ark-audit-queue", auditRequest);
        rabbitTemplate.convertAndSend("", auditQueue, objectMapper.writeValueAsString(auditRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
