package com.artgallery.artgallery.estado.application;

import java.util.List;
import java.util.Optional;

import com.artgallery.artgallery.estado.domain.Estado;

public interface IEstado {
    Estado crearEstado(Estado estado);
    Optional<Estado> buscarEstadoPorId(Long id);
    List<Estado> mostrarEstados();
    Estado eliminarEstado(Long id);
    Estado actualizarEstado(Estado estado);
}
