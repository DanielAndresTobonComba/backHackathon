package com.artgallery.artgallery.usuario.infraestructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.rol.domain.Rol;
import com.artgallery.artgallery.rol.infraestructure.RolImplement;
import com.artgallery.artgallery.usuario.domain.Usuario;
import com.artgallery.artgallery.usuario.domain.UsuarioDTO;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/crear")
public class UsuarioController {
    @Autowired
    private UsuarioImplement usuarioImplement;

    @Autowired
    private RolImplement rolImp;

    @PostMapping("/{rolId}")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }
        Usuario user = new Usuario();
        user.setNombre(usuarioDTO.getNombre());
        user.setApellido(usuarioDTO.getApellido());
        user.setCorreo(usuarioDTO.getCorreo());
        user.setCedula(usuarioDTO.getCedula());
        user.setContraseña(usuarioDTO.getContraseña());
        user.setFotoPerfil(usuarioDTO.getFotoPerfil());
        Rol rol =  rolImp.obtenerRolPorId(usuarioDTO.getIdRol());
        user.getRoles().add(rol);
        Usuario nuevoUsuario = usuarioImplement.crearUsuario(user);
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