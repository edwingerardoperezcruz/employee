package com.invex.employee.exception;

import com.invex.employee.dto.Error;
import com.invex.employee.dto.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerTest {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleEmployeeException() {
        EmployeeException exception = new EmployeeException(Error.GENERAL_ERROR);

        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleEmployeeException(exception);
        ErrorResponse response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Error to Employee Service", response.getUserMessage());
        assertNotNull(response.getTimestamp());
        assertTrue(response.getMessage().contains("Error"));
    }


    @Test
    void testHandleEmployeeGenericException() {
        RuntimeException exception = new RuntimeException("Unexpected DB error");

        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleGenericException(exception);
        ErrorResponse response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Error to Employee Service", response.getUserMessage());
        assertNotNull(response.getTimestamp());
        assertTrue(response.getMessage().contains("Unexpected DB error"));
    }
}
