package com.artgallery.artgallery.Auth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.jwt.JwtService;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService JwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginResquest resquest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(resquest.getUsername(), resquest.getPassword()));

        // generaremos el token
        User user = userRepository.findByUsername(resquest.getUsername());
        if (user == null) {
            return  new AuthResponse("El usuario no existe" , "No disponible" , "No disponible" , "No disponible" , "No diponible");
        }

        String token = JwtService.getToken(user);

        return AuthResponse.builder()
            .token(token)
            .rol(user.getRol().getNombre())
            .nombre(user.getNombre())
            .foto(user.getFotoPerfil())
            .cedula(user.getCedula())
            .build();
    }

    
    public AuthResponse registro(RegisterRequest request) {
        // Validar datos
        if (request.getUsername() == null || request.getPassword() == null || request.getRol() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        if (request.getRolRegistrador().equals("Project Manager")) {

                    // Construir el usuario con los datos
        User user = User.builder()

        .nombre(request.getNombre())
        .cedula(request.getCedula())
        .username(request.getUsername())
        .correo(request.getCorreo())
        .fotoPerfil(request.getProfilepic())
        .password(passwordEncoder.encode(request.getPassword()))
        .rol(request.getRol())
        .build();

        // Guardar el usuario en la base de datos
        userRepository.save(user);

        // Retornar un objeto de la clase AuthResponse con el token
        return AuthResponse.builder()
            .cedula(user.getCedula())
            .token(JwtService.getToken(user))
            .rol(request.getRol().getNombre())
            .nombre(user.getNombre())
            .foto(user.getFotoPerfil())
            .build();
            }

        return new AuthResponse("No tiene permisos" , "No tiene permisos" , "No disponible" , "No disponible" , "No diponible");
    }

}
