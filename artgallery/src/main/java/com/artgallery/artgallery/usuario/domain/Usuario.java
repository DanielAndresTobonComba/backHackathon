package com.artgallery.artgallery.usuario.domain;

import com.artgallery.artgallery.rol.domain.Rol;
import jakarta.persistence.Column;
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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "La cedula no debe estar vacia")
    @Size(min = 1, max = 18, message = "la cedula debe tener entre 1 y 18 caracteres")
    private String cedula;

    @NotNull(message = "El nombre no puede estar vacio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El Apellido no puede estar vacio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String apellido;

    @NotNull(message = "El Correo no puede estar vacio")
    @Column(unique = true)
    private String correo;
    
    private String fotoPerfil;

    @ManyToOne
    @JoinColumn(name = "rolId")
    private Rol rol;

}
