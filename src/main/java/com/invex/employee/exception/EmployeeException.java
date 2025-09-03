package com.invex.employee.exception;

import com.invex.employee.dto.Error;

public class EmployeeException extends RuntimeException {

    Error errorEmployee;

    public EmployeeException(Long id, Error error) {
        super("Error with Employee id:" + id);
        this.errorEmployee = error;
    }

    public EmployeeException(Error error) {
        super("Error Employee");
        this.errorEmployee = error;
    }

    public Error getErrorEmployee() {
        return errorEmployee;
    }

}
