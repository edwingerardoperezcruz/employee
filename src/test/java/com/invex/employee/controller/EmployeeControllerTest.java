package com.invex.employee.controller;

import com.invex.employee.dto.Employee;
import com.invex.employee.dto.Response;
import com.invex.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setFirstName("Emmanuel");
        employee.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetListEmployees() throws Exception {
        when(service.getListEmployees())
                .thenReturn(Collections.singletonList(employee));
        ResponseEntity<Response> response = controller.getListEmployees();

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Successful", response.getBody().getStatus());
        assertEquals(200, response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(service.getEmployeeById(1L)).thenReturn(employee);

        ResponseEntity<Response> response = controller.getEmployeeById(1L);

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());

    }

    @Test
    void testCreateEmployee() throws Exception {
        when(service.createEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<Response> response = controller.createEmployee(new Employee());

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(service.updateEmployee(any(Employee.class), eq(1L))).thenReturn(employee);
        ResponseEntity<Response> response = controller.updateEmployee(new Employee(), 1L);

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(service).deleteEmployee(1L);
        ResponseEntity<Response> response = controller.deleteEmployee(1L);

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());

    }

    @Test
    void testGetEmployeesByName() throws Exception {
        when(service.getEmployeesByName("Emmanuel"))
                .thenReturn(Collections.singletonList(employee));
        ResponseEntity<Response> response = controller.getEmployeesByName("Emmanuel");

        assertNotNull(response.getBody().getData());
        assertEquals(200, response.getStatusCode().value());
    }
}
