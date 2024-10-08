package com.artgallery.artgallery.usuario.infraestructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.actividad.domain.Actividad;
import com.artgallery.artgallery.actividad.infrastructure.ActividadServiceImp;
import com.artgallery.artgallery.rol.domain.Rol;
import com.artgallery.artgallery.rol.infraestructure.RolImplement;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.domain.UsuarioDTO;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    @Autowired
    private UsuarioImplement usuarioImplement;

    @Autowired
    private RolImplement rolImp;

    @Autowired
    private ActividadServiceImp actividadServiceImp;

    @PostMapping("")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {

        if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }

        User user = new User();
        user.setUsername(usuarioDTO.getNombre());
        user.setNombre(usuarioDTO.getApellido());
        user.setCorreo(usuarioDTO.getCorreo());
        user.setCedula(usuarioDTO.getCedula());
        user.setPassword(usuarioDTO.getContraseña());
        user.setFotoPerfil(usuarioDTO.getFotoPerfil());
        Rol rol =  rolImp.obtenerRolPorId(usuarioDTO.getIdRol());
        user.setRol(rol);
        User nuevoUsuario = usuarioImplement.crearUsuario(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
         
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<?> obtenerUsuarioPorCedula(@PathVariable String cedula) {
        return ResponseEntity.ok().body(usuarioImplement.buscarUsuarioPorCedula(cedula));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable  Long id) {
        if(usuarioImplement.buscarUsuarioPorId(id)!=null){
            return ResponseEntity.ok().body(usuarioImplement.buscarUsuarioPorId(id));
        }

        return ResponseEntity.notFound().build();
        
    }

    @GetMapping("")
    public ResponseEntity<?> mostrarUsuarios() {
        return ResponseEntity.ok().body(usuarioImplement.mostrarUsuarios());
    }

    @Transactional
    @DeleteMapping("/{cedula}")  
    public ResponseEntity<?>  eliminarUsuario(@PathVariable String cedula){
        User usuario =  usuarioImplement.buscarUsuarioPorCedula(cedula);
        if(usuario != null){
            List<Actividad> actividades = actividadServiceImp.mostrarActividaesPorIdUser(usuario.getId());
            for (Actividad actividad : actividades) {
                actividad.setUsuario(null);         
            }
            usuarioImplement.eliminar(cedula);
             return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
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