package com.artgallery.artgallery.asistencia.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artgallery.artgallery.asistencia.domain.Asistencia;


@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia,Long>{
    List<Asistencia> findByUsuarioId(Long userId);
}
