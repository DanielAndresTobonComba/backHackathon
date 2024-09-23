package com.artgallery.artgallery.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    // http://localhost:8090/auth/login


    // la rta sera de tipo authresponse (token) y recibimos los datos del usuario que es la clase de tipo (LoginRequest)
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginResquest resquest) {
        // se devuelve un objeto vacio
        return ResponseEntity.ok(authservice.login(resquest));
    }

    // http://localhost:8090/auth/registro
    // la rta se un authResponse y recibiremos los datos de usario de tipo RegisterRequest.
    @PostMapping("/registro")
    public  ResponseEntity<AuthResponse> registro (@RequestBody RegisterRequest resquest) {
        System.out.println("Registro llamado con: " + resquest);
        
        return ResponseEntity.ok(authservice.registro(resquest));
    }
}
