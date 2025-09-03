package com.invex.employee.service;

import com.invex.employee.dto.Employee;
import com.invex.employee.dto.Error;
import com.invex.employee.exception.EmployeeException;
import com.invex.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setFirstName("Emmanuel");
    }

    @Test
    void testGetListEmployeesSuccess() {
        when(repository.findAll()).thenReturn(Collections.singletonList(employee));

        List<Employee> result = service.getListEmployees();

        assertEquals(1, result.size());
        assertEquals("Emmanuel", result.get(0).getFirstName());
    }

    @Test
    void testGetListEmployeesThrowsException() {
        when(repository.findAll()).thenThrow(new RuntimeException("DB error"));

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.getListEmployees());

        assertEquals(Error.GENERAL_ERROR, ex.getErrorEmployee());
    }

    @Test
    void testGetEmployeeByIdSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdEmployee());
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.getEmployeeById(1L));

        assertEquals(Error.NOT_FOUND, ex.getErrorEmployee());
    }

    @Test
    void testGetEmployeeByIdGeneralException() {
        when(repository.findById(1L)).thenThrow(new RuntimeException("DB error"));

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.getEmployeeById(1L));

        assertEquals(Error.GENERAL_ERROR, ex.getErrorEmployee());
    }

    @Test
    void testCreateEmployeeSuccess() {
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee result = service.createEmployee(employee);

        assertNotNull(result.getCreatedAt());
        assertEquals(employee, result);
    }

    @Test
    void testCreateEmployeeThrowsException() {
        when(repository.save(any(Employee.class))).thenThrow(new RuntimeException("DB error"));

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.createEmployee(employee));

        assertEquals(Error.CREATE_ERROR, ex.getErrorEmployee());
    }

    @Test
    void testUpdateEmployeeSuccess() {
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee result = service.updateEmployee(employee, 1L);

        assertEquals(1L, result.getIdEmployee());
    }

    @Test
    void testUpdateEmployeeThrowsException() {
        when(repository.save(any(Employee.class))).thenThrow(new RuntimeException("DB error"));

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.updateEmployee(employee, 1L));

        assertEquals(Error.UPDATE_ERROR, ex.getErrorEmployee());
    }

    @Test
    void testDeleteEmployeeSuccess() {
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deleteEmployee(1L));
    }

    @Test
    void testDeleteEmployeeThrowsException() {
        doThrow(new RuntimeException("DB error")).when(repository).deleteById(1L);

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.deleteEmployee(1L));

        assertEquals(Error.DELETE_ERROR, ex.getErrorEmployee());
    }

    @Test
    void testGetEmployeesByNameSuccess() {
        when(repository.findByName("Emmanuel")).thenReturn(Collections.singletonList(employee));

        List<Employee> result = service.getEmployeesByName("Emmanuel");

        assertEquals(1, result.size());
        assertEquals("Emmanuel", result.get(0).getFirstName());
    }

    @Test
    void testGetEmployeesByNameNotFound() {
        when(repository.findByName("Emmanuel")).thenReturn(Collections.emptyList());

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.getEmployeesByName("Emmanuel"));

        assertEquals(Error.NOT_FOUND, ex.getErrorEmployee());
    }

    @Test
    void testGetEmployeesByNameThrowsGeneralError() {
        when(repository.findByName("Emmanuel")).thenThrow(new RuntimeException("DB error"));

        EmployeeException ex = assertThrows(EmployeeException.class,
                () -> service.getEmployeesByName("Emmanuel"));

        assertEquals(Error.GENERAL_ERROR, ex.getErrorEmployee());
    }
}
