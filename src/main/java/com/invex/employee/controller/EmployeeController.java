package com.invex.employee.controller;

import com.invex.employee.dto.Employee;
import com.invex.employee.dto.ErrorResponse;
import com.invex.employee.dto.Response;
import com.invex.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    private static final String STATUS = "Successful";
    private static final int CODE = 200;

    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @Operation(summary = "Get employees list", description = "Get employees list from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees list",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping
    public ResponseEntity<Response> getListEmployees() {
        Response response = new Response();
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData(service.getListEmployees());
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get employee", description = "Get a employee from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee founded"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> getEmployeeById(
            @Parameter(description = "Employee identifier", example = "1")
            @PathVariable Long id) {
        Response response = new Response();
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData(service.getEmployeeById(id));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a employee", description = "Create a new employee in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created"),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    })
    @PostMapping
    public ResponseEntity<Response> createEmployee(@RequestBody List<Employee> employee) {
        Response response = new Response();
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData(service.createEmployee(employee));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a employee", description = "Update employee information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated"),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateEmployee(
            @Parameter(description = "Employee identifier", example = "1")
            @RequestBody Employee employee, @PathVariable Long id) {
        Response response = new Response();
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData(service.updateEmployee(employee, id));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete employee", description = "Delete a employee.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteEmployee(
            @Parameter(description = "Employee identifier", example = "1")
            @PathVariable Long id) {
        Response response = new Response();
        service.deleteEmployee(id);
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData("Employee deleted");
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get employee", description = "Get a employee by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee founded"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Error Generic",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping("/search")
    public ResponseEntity<Response> getEmployeesByName(
            @Parameter(description = "Employee name", example = "1")
            @RequestParam String name) {
        Response response = new Response();
        response.setCode(CODE);
        response.setStatus(STATUS);
        response.setData(service.getEmployeesByName(name));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

}
