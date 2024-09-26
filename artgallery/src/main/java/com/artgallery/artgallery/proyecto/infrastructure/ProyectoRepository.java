package com.artgallery.artgallery.proyecto.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artgallery.artgallery.proyecto.domain.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Long> {
    List<Proyecto> findByUsuariosId(Long userId);
    List<Proyecto> findByEstadoId(Long estadoId);
}
