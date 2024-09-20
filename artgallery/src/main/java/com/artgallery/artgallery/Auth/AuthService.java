package com.artgallery.artgallery.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.User.Role;
import com.artgallery.artgallery.User.User;
import com.artgallery.artgallery.User.UserRepository;
import com.artgallery.artgallery.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService JwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginResquest resquest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(resquest.getUsername(), resquest.getPassword()));

        // generaremos el token
        UserDetails user = userRepository.findByUsername(resquest.getUsername()).orElseThrow();

        String token = JwtService.getToken(user);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse registro(RegisterRequest request) {
        

        // Tomalos la calse registerRequest y construimos una clase user con los datos
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firtsname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)
            .build();

        userRepository.save(user); // llamo al repositorio del usuario y guardo en la db el usuario que acabe de crear

        // retornamos el un objeto de la clase authresponse con el token 
        return AuthResponse.builder()
            .token(JwtService.getToken(user))
            .build();
        }

}
