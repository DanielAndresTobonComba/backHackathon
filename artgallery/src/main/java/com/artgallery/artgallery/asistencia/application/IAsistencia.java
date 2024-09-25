package com.artgallery.artgallery.asistencia.application;

import java.util.List;
import java.util.Optional;
import com.artgallery.artgallery.asistencia.domain.Asistencia;
import com.artgallery.artgallery.estado.domain.Estado;

public interface IAsistencia {
    Asistencia crearAsistencia(Asistencia asistencia);
    Optional<Asistencia> buscarEstadoPorId(Long id);
    List<Estado> mostrarEstados();
    Estado eliminarEstado(Long id);
    Estado actualizarEstado(Estado estado);
}
