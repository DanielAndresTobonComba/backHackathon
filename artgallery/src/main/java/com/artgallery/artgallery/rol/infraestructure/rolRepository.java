package com.artgallery.artgallery.rol.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artgallery.artgallery.rol.domain.Rol;

public interface rolRepository extends JpaRepository<Rol,Long>  {

}
