package com.artgallery.artgallery.asistencia.application;

import java.util.List;
import com.artgallery.artgallery.asistencia.domain.Asistencia;

public interface IAsistencia {
    Asistencia crearAsistencia(Asistencia asistencia);
    List<Asistencia> buscarAsistenciaPorIdUsuario(Long idUser);
    List<Asistencia> mostrarAsistencias();
    Asistencia eliminarAsistencia(Long id);
}


