package com.artgallery.artgallery.usuario.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.usuario.application.Iusuario;
import com.artgallery.artgallery.usuario.domain.Usuario;

@Service
public class UsuarioImplement implements Iusuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
     
        return usuarioRepository.save(usuario);
    }

}
