package com.artgallery.artgallery.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

// toca implementar userdetails
public class User implements UserDetails {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    String firtsname;
    String lastname;
    String country;

    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // aqui se especifica cual es el rol del usuario
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
}
