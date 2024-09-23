package com.artgallery.artgallery.proyecto.domain;

import com.artgallery.artgallery.usuario.domain.User;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 3, message = "Las horas estipuladas son obligatorias")
    private int horasEstipuladas;

    private int horasUsadas;

    private Date fechaInicio;

    private Date fechaFin;

    @ManyToMany(mappedBy = "proyectos")
    private Set<User> usuarios = new HashSet<>();

}
