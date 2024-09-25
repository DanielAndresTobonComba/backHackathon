package com.artgallery.artgallery.actividad.domain;

import java.sql.Date;

import com.artgallery.artgallery.estado.domain.Estado;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.usuario.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 1500, message = "El nombre debe tener entre 1 y 1500 caracteres")
    private String descripcion;

    private int horasUsadas;

    private Date fechaInicio;

    private Date fechaFin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "desarrolladorId")
    private User usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proyectoId")
    private Proyecto proyecto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
