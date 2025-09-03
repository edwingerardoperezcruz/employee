package com.invex.employee.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Response {
    private String status;
    private int code;
    private Object data;
    private LocalDateTime timestamp;
}
