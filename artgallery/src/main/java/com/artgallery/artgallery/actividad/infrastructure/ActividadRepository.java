package com.artgallery.artgallery.actividad.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.artgallery.actividad.domain.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad,Long>{
        
}
