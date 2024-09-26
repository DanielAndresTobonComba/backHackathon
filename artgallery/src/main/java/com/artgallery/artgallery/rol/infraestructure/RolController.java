package com.artgallery.artgallery.rol.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.rol.application.Irol;
import com.artgallery.artgallery.rol.domain.Rol;
import com.artgallery.artgallery.rol.domain.rolDTO;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rol")

public class RolController {

    @Autowired
    private Irol rolService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRolPorId(@PathVariable Long id) {
        Rol rol = rolService.obtenerRolPorId(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> CrearRol( @Valid @RequestBody rolDTO roldto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }
        Rol rol = new Rol();
        rol.setNombre(roldto.getNombre());
        return ResponseEntity.ok().body(rolService.crearRol(rol));
    }

    @GetMapping("")
    public ResponseEntity<?> obtenerRoles() {
        return ResponseEntity.ok().body(rolService.obtenerTodosRoles()); 
    }


}