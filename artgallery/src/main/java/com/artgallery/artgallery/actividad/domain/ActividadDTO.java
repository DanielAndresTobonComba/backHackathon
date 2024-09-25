package com.artgallery.artgallery.actividad.domain;

import java.sql.Date;

import com.artgallery.artgallery.estado.domain.Estado;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.usuario.domain.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActividadDTO {
  
    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    public String nombre;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 1500, message = "El nombre debe tener entre 1 y 1500 caracteres")
    public String descripcion;

    public int horasUsadas;

    public Date fechaInicio;

    public Date fechaFin;
}
