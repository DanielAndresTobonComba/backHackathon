package com.artgallery.artgallery.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.artgallery.artgallery.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilitar CORS
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll()
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            .sessionManagement(sessionManager ->    
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) 
            .build();
    }

    // Método para configurar las reglas CORS
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Permitir cualquier origen
        config.addAllowedHeader("*"); // Permitir cualquier cabecera
        config.addAllowedMethod("*"); // Permitir cualquier método (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar la configuración a todas las rutas
        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}

// import static org.springframework.security.config.Customizer.withDefaults;

/* @Configuration
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
                .requestMatchers( "/**").permitAll()
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
} */
