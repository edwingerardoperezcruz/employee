package com.invex.employee.service;

import com.invex.employee.dto.Employee;
import com.invex.employee.dto.Error;
import com.invex.employee.exception.EmployeeException;
import com.invex.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }

    public List<Employee> getListEmployees() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            throw new EmployeeException(Error.GENERAL_ERROR);
        }
    }

    public Employee getEmployeeById(final Long id) {
        try {
            Optional<Employee> result = repository.findById(id);
            if (result.isEmpty()) {
                throw new EmployeeException(id, Error.NOT_FOUND);
            }
            return result.get();
        } catch (EmployeeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new EmployeeException(id, Error.GENERAL_ERROR);
        }
    }

    public Employee createEmployee(Employee employee) {
        try {
            employee.setCreatedAt(LocalDateTime.now());
            return repository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeException(Error.CREATE_ERROR);
        }
    }

    public Employee updateEmployee(Employee employee, Long id) {
        try {
            employee.setIdEmployee(id);
            return repository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeException(id, Error.UPDATE_ERROR);
        }
    }

    public void deleteEmployee(final Long id){
        try {
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new EmployeeException(id, Error.DELETE_ERROR);
        }
    }

    public List<Employee> getEmployeesByName(final String name) {
        try {
            List<Employee> result = repository.findByName(name);
            if(result.isEmpty()) {
                throw new EmployeeException(Error.NOT_FOUND);
            }
            return result;
        } catch (EmployeeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new EmployeeException(Error.GENERAL_ERROR);
        }
    }

}
