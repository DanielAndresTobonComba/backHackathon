package com.artgallery.artgallery.proyecto.domain;

import com.artgallery.artgallery.usuario.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 150, message = "El nombre debe tener entre 1 y 150 caracteres")
    private String nombre;

    private String descripcion;
    
    private String horasUsadas;

    private Date fechaInicio;

    private Date fechaFin;

    @ManyToOne()
    @JoinColumn(name = "id_techlead")
    private User techLead;

    @ManyToMany(mappedBy = "proyectos")
    @JsonIgnore
    private Set<User> usuarios = new HashSet<>();


}
