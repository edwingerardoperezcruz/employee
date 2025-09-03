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

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    private final String status = "Successful";
    private final int code = 200;

    public EmployeeController(EmployeeService employeeService) {
        this.service = employeeService;
    }

    @Operation(summary = "Obtiene lista de empleados", description = "Devuelve el listado de todos los empleados registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping
    public ResponseEntity<Response> getListEmployees() {
        Response response = new Response();
        response.setCode(code);
        response.setStatus(status);
        response.setData(service.getListEmployees());
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene empleado", description = "Devuelve un empleado registrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            )),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> getEmployeeById(
            @Parameter(description = "Identificador del empleado a buscar", example = "1")
            @PathVariable Long id) {
        Response response = new Response();
        response.setCode(code);
        response.setStatus(status);
        response.setData(service.getEmployeeById(id));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registra un empleado", description = "Registra un nuevo empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado registrado"),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    })
    @PostMapping
    public ResponseEntity<Response> createEmployee(@RequestBody Employee employee) {
        Response response = new Response();
        response.setCode(code);
        response.setStatus(status);
        response.setData(service.createEmployee(employee));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualiza un empleado", description = "Actualiza la información de un empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateEmployee(
            @Parameter(description = "Identificador del empleado a actualizar", example = "1")
            @RequestBody Employee employee, @PathVariable Long id) {
        Response response = new Response();
        response.setCode(code);
        response.setStatus(status);
        response.setData(service.updateEmployee(employee, id));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Elimina un empleado", description = "Elimina la información de un empleado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado eliminado"),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteEmployee(
            @Parameter(description = "Identificador del empleado a eliminar", example = "1")
            @PathVariable Long id) {
        Response response = new Response();
        service.deleteEmployee(id);
        response.setCode(code);
        response.setStatus(status);
        response.setData("Employee deleted");
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene un empleado", description = "Obtiene un empleado filtrando por su nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Error general",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping("/search")
    public ResponseEntity<Response> getEmployeesByName(
            @Parameter(description = "Nombre del empleado a buscar", example = "1")
            @RequestParam String name) {
        Response response = new Response();
        response.setCode(code);
        response.setStatus(status);
        response.setData(service.getEmployeesByName(name));
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

}
