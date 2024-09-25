package com.artgallery.artgallery.estado.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.artgallery.estado.domain.Estado;

@Repository
public interface EstadoRespository extends JpaRepository<Estado,Long> {

}
