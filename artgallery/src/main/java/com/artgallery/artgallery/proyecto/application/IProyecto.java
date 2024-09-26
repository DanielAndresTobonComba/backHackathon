package com.artgallery.artgallery.proyecto.application;

import java.util.List;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.usuario.domain.User;

public interface IProyecto {
    Proyecto crearProyecto(Proyecto proyecto);
    Proyecto  buscarProyectoPorId(Long id);
    List<Proyecto> mostrarProyectos();
    void eliminarProyecto(Long id);
    User asignarUsuarioaProyecto(String cedula, Long idProyecto);
    List<Proyecto> mostrarProyectoPorUserId(Long id);
    List<Proyecto> mostratProyectoSegunId(Long id);
    
}


