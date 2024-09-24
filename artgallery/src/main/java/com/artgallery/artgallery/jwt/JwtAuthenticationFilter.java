package com.artgallery.artgallery.jwt;



import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// // ESTE ES EL FILTRO DEL JWT SI TODO SALE BIEN IRA A LA AUTENTICACION (AUTHCONTROLLER)

@Component
@RequiredArgsConstructor // hace que todos lo campos se inicializen en el constructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // se extiende de este para que el filtro se ejecute solo 1 vez por cada solicitud http

    private final JwtService jwtService; 
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        final String token = getTokenFromRequest(request);
        // obtenemos el user name 
        final String username; 

        
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // si se cumple el flitro del jwt , accedemo al username que esta en el token
        username = jwtService.getUsernameFromToken(token); 


        // si el nombre no es nulo y que ademas no se encuentra en el securityConxtestholder iremos a la db para buscar el nombre
        if (username!= null && SecurityContextHolder.getContext().getAuthentication() == null) { 

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            //  aca se reviza si el token es valido
            if (jwtService.isTokenValid(token , userDetails)) { 

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // seteamos la autenticacion al secutirycontextholder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
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
