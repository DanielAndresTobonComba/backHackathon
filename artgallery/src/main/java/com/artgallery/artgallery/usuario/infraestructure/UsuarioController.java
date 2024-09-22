package com.artgallery.artgallery.usuario.infraestructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.usuario.domain.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioImplement usuarioImplement;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioImplement.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);  
    }
    
}

/*{
    "cedula": "1005323441",
    "nombre": "camilo",
    "apellido": "hernandez",
    "correo": "camiloht0918@gmaiOl.com",
    "fotoPerfil": "sdsdsd",
     "rol": {
    "id": 1
  }
} */