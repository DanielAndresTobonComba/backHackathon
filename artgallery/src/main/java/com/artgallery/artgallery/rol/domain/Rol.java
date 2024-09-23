package com.artgallery.artgallery.rol.domain;

import java.util.HashSet;
import java.util.Set;

import com.artgallery.artgallery.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore 
    private Set<Usuario> usuarios = new HashSet<>();

}




