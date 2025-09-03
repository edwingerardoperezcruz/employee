package com.invex.employee.exception;

import com.invex.employee.dto.Error;
import com.invex.employee.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = exception.getErrorEmployee();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(error.getStatus().value());
        errorResponse.setUserMessage(error.getError());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(Error.GENERAL_ERROR.getStatus().value());
        errorResponse.setUserMessage(Error.GENERAL_ERROR.getError());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}
