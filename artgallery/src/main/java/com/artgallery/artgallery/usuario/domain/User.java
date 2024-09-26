package com.artgallery.artgallery.usuario.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.rol.domain.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre de usuario no puede estar vacio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String username;

    @NotNull(message = "El Nombre no puede estar vacio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La cedula no debe estar vacia")
    @Size(min = 1, max = 18, message = "la cedula debe tener entre 1 y 18 caracteres")
    private String cedula;

    @NotNull(message = "la contraseña no puede estar vacia")
    @Column()
    private String password;

    @NotNull(message = "El Correo no puede estar vacio")
    @Column(unique = true)
    private String correo;

    private String fotoPerfil;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol")
    private Rol rol;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuario_proyecto",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    )
    
    @JsonIgnore
    @Builder.Default
    private Set<Proyecto> proyectos = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // aqui se especifica cual es el rol del usuario
        return List.of(new SimpleGrantedAuthority((rol.getNombre())));
    }

    
}
