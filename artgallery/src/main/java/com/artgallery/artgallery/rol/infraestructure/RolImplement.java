package com.artgallery.artgallery.rol.infraestructure;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.rol.application.Irol;
import com.artgallery.artgallery.rol.domain.Rol;



@Service
public class RolImplement implements Irol {

    @Autowired
    private rolRepository rolRepository;

    @Override
    public Rol obtenerRolPorId(Long id) {
        // Buscar el rol por ID
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el id: " + id));
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> obtenerTodosRoles() {
        return rolRepository.findAll();
    }
}
