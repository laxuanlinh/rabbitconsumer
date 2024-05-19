package com.example.rabbitconsumer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditRequest {

    private String actor;
    private String action;
    private LocalDateTime time;
}
