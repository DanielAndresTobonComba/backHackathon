package com.artgallery.artgallery.actividad.application;

import java.util.List;
import java.util.Optional;

import com.artgallery.artgallery.actividad.domain.Actividad;

public interface IActividad {
    Actividad crearActividad(Actividad actividad);
    Optional<Actividad> buscarActividadPorId(Long id);
    List<Actividad> mostrarActividades();
    List<Actividad> mostrarActividaesPorIdUser(Long userId);
    Actividad eliminarActividad(Long id);
    Actividad actualizarActividad(Actividad actividad);
    Actividad InsertarActividadAUsuario(Long idUser, Long idActividad);
}
