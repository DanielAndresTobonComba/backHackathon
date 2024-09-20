package com.artgallery.artgallery.jwt;



import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ESTE ES EL FILTRO DEL JWT SI TODO SALE BIEN IRA A LA AUTENTICACION (AUTHCONTROLLER)

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // se extiende de este para que el filtro se ejecute solo 1 vez por cada solicitud http



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        final String token = getTokenFromRequest(request);
        
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);

    }

    // le pasamos el encabezado del request porque de ahi tomamos el token
    private String getTokenFromRequest(HttpServletRequest request) {
        // vamos a sacar el token del header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // si existe el texto y empieza por "Bearer se extraera desde la posicion 7 el token"
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

}
