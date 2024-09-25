package com.artgallery.artgallery.actividad.infrastructure;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.artgallery.actividad.domain.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad,Long>{

    List<Actividad> findByDesarrollador_Id(Long userId);
    // @Query("SELECT a FROM Actividad a WHERE a.desarrollador.id = :userId")
    // List<Actividad> findActividadesByUserId(@Param("userId") Long userId);
}
