package com.artgallery.artgallery.usuario.application;


import java.util.List;

import com.artgallery.artgallery.usuario.domain.Usuario;

public interface Iusuario {
    Usuario crearUsuario(Usuario usuario);
    Usuario  buscarUsuarioPorCedula(String cedula);
    List<Usuario> mostrarUsuarios();
    void eliminar(String cedula);
    Usuario actualizar(String Cedula, Usuario usuario);

}
