package com.artgallery.artgallery.rol.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.rol.application.Irol;
import com.artgallery.artgallery.rol.domain.Rol;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private Irol rolService;

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        Rol rol = rolService.obtenerRolPorId(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }
}