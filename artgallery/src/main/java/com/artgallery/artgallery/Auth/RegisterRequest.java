package com.artgallery.artgallery.Auth;

import com.artgallery.artgallery.rol.domain.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String nombre;
    String password;
    String profilepic;
    String cedula;
    String correo;
    String rolRegistrador;
    Rol rol;
}
