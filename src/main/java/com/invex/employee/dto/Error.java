package com.invex.employee.dto;

import org.springframework.http.HttpStatus;

public enum Error {
    NOT_FOUND("Employee not found", HttpStatus.NOT_FOUND),
    CREATE_ERROR("Error to create Employee", HttpStatus.CONFLICT),
    UPDATE_ERROR("Error to update Employee", HttpStatus.CONFLICT),
    DELETE_ERROR("Error to delete Employee",  HttpStatus.CONFLICT),
    GENERAL_ERROR("Error to Employee Service",  HttpStatus.INTERNAL_SERVER_ERROR);

    private final String error;
    private final HttpStatus status;

    Error(String errorEmployee, HttpStatus httpStatus) {
        this.error = errorEmployee;
        this.status = httpStatus;
    }

    public String getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
