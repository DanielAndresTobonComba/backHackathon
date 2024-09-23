package com.artgallery.artgallery.usuario.infraestructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.usuario.application.Iusuario;
import com.artgallery.artgallery.usuario.domain.Usuario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioImplement implements Iusuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorCedula(String cedula) {

        Optional<Usuario> usuario = usuarioRepository.findByCedula(cedula);
        if (!usuario.isPresent()) {
            throw new EntityNotFoundException("usuario no encontrado con cedula: " + cedula);
        }
        return usuario.get();
    }

    @Override
    public List<Usuario> mostrarUsuarios() {
        return  usuarioRepository.findAll();
    }

    @Override
    public void eliminar(String cedula) {
        usuarioRepository.deleteByCedula(cedula);
    }

    @Override
    public Usuario actualizar(String Cedula, Usuario usuario) {
        Optional<Usuario> usuarioBD = usuarioRepository.findByCedula(Cedula);
        if (!usuarioBD.isPresent()) {
            throw new EntityNotFoundException("usuario no encontrado con cedula: " + Cedula);
        }
        return usuarioRepository.save(null);
    }

}
