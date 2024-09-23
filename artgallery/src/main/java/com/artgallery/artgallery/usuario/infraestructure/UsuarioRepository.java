package com.artgallery.artgallery.usuario.infraestructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artgallery.artgallery.usuario.domain.User;



@Repository
public interface UsuarioRepository extends JpaRepository<User,Long> {
    Optional<User> findByCedula(String cedula);
    void deleteByCedula(String cedula);
    Boolean existsByCorreo(String Correo);
}
