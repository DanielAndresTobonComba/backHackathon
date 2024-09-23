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
    String password;
    String name; // Cambia 'firtsname' a 'firstname'
    String email;
    String profilepic;
    Rol rol;
}
