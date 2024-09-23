package com.artgallery.artgallery.usuario.domain;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    
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
    private String correo;

    @NotNull(message = "la contraseña no puede estar vacia")
    private String contraseña;
    
    private String fotoPerfil;

    private Long idRol;


}
