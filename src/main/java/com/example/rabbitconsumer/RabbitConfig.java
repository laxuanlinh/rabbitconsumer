package com.example.rabbitconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.audit}")
    private String auditQueue;

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper =  new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

    @Bean
    public Queue createAuditQueue(){
        return new Queue(auditQueue);
    }

}
