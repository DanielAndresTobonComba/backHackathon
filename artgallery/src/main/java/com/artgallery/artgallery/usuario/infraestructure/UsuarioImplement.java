package com.artgallery.artgallery.usuario.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.rol.infraestructure.rolRepository;
import com.artgallery.artgallery.usuario.application.Iusuario;
import com.artgallery.artgallery.usuario.domain.User;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioImplement implements Iusuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private rolRepository rolRepo;



    @Override
    public User crearUsuario(User usuario) {
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
            throw new RuntimeException("El email ya está en uso");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public User buscarUsuarioPorCedula(String cedula) {

        Optional<User> usuario = usuarioRepository.findByCedula(cedula);
        if (!usuario.isPresent()) {
            throw new EntityNotFoundException("usuario no encontrado con cedula: " + cedula);
        }
        return usuario.get();
    }

    @Override
    public List<User> mostrarUsuarios() {
        return  usuarioRepository.findAll();
    }

    @Override
    public void eliminar(String cedula) {
        usuarioRepository.deleteByCedula(cedula);
    }

    @Override
    public User actualizar(String Cedula, User usuario) {
        Optional<User> usuarioBD = usuarioRepository.findByCedula(Cedula);
        if (!usuarioBD.isPresent()) {
            throw new EntityNotFoundException("usuario no encontrado con cedula: " + Cedula);
        }
        return usuarioRepository.save(usuario);
    }

}
