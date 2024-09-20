package com.artgallery.artgallery.Config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.artgallery.artgallery.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter JwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(requests -> requests
                .requestMatchers( "/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll() // Permitir todas las rutas bajo /auth
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            //.formLogin(withDefaults())
            // deshabilitamos la sesiones 
            .sessionManagement(sessionManager ->    
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            // filtro relacionado a jwt
            .addFilterBefore(JwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class) 
            .build();
            
    }
}
