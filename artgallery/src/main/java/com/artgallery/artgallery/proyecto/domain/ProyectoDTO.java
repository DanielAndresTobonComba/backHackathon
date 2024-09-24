package com.artgallery.artgallery.proyecto.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProyectoDTO {

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    @NotNull(message = "La descripcion no puede estar vacia")
    private String descripcion;

    
    private String horasUsadas;

    private Date fechaInicio;

    private Date fechaFin;

    @NotNull(message = "el id del lider  no puede estar vacio")
    private Long  idLeader;

}
