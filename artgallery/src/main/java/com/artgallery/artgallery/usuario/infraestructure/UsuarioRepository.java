package com.artgallery.artgallery.usuario.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.artgallery.usuario.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
