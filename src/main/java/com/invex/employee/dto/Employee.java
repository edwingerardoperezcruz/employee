package com.invex.employee.dto;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Empleado")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmployee;

    @Column(name = "primerNombre")
    private String firstName;
    @Column(name = "segundoNombre")
    private String middleName;
    @Column(name = "apellido_paterno")
    private String fatherLastName;
    @Column(name = "apellido_materno")
    private String motherLastName;
    @Column(name = "edad")
    private int age;
    @Column(name = "sexo")
    private String gender;
    @Column(name = "fecha_nacimiento")
    private Date bornDate;
    @Column(name = "puesto")
    private String position;
    @Column(name = "fecha_registro")
    private LocalDateTime createdAt;
    @Column(name = "estado")
    private boolean status;

    public String getStatus(){
        return status != true?"Inactivo":"Activo";
    }
}
