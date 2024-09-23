package com.artgallery.artgallery.rol.application;

import java.util.List;
import com.artgallery.artgallery.rol.domain.Rol;


public interface Irol {
    Rol obtenerRolPorId(Long id);
    Rol crearRol(Rol rol);
    List<Rol> obtenerTodosRoles();
}