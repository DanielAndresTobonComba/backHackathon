package com.artgallery.artgallery.Config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/auth/**").permitAll() // Permitir todas las rutas bajo /auth
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            .formLogin(withDefaults())
            .build();
            
    }
}
