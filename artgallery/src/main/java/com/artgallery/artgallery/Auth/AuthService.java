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
            return  new AuthResponse("El usuario no existe");
        }

        String token = JwtService.getToken(user);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    
    public AuthResponse registro(RegisterRequest request) {
        // Validar datos
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        // Construir el usuario con los datos
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .Nombre(request.name)
            .rol(request.rol)
            .build();

        // Guardar el usuario en la base de datos
        userRepository.save(user);
    
        // Retornar un objeto de la clase AuthResponse con el token
        return AuthResponse.builder()
            .token(JwtService.getToken(user))
            .build();
    }

}
