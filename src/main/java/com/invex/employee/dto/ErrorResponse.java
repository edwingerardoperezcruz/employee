package com.invex.employee.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private int status;
    private String userMessage;
}
