package com.artgallery.artgallery.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artgallery.artgallery.usuario.domain.User;

public interface UserRepository extends JpaRepository <User , Long> {
    Optional <User> findByUsername (String username);

}
