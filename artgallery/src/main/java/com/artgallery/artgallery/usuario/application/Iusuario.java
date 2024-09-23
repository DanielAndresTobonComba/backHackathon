package com.artgallery.artgallery.usuario.application;


import java.util.List;

import com.artgallery.artgallery.usuario.domain.User;

public interface Iusuario {
    User crearUsuario(User usuario);
    User  buscarUsuarioPorCedula(String cedula);
    List<User> mostrarUsuarios();
    void eliminar(String cedula);
    User actualizar(String Cedula, User usuario);

}
