package com.artgallery.artgallery.asistencia.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.asistencia.application.IAsistencia;
import com.artgallery.artgallery.asistencia.domain.Asistencia;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioRepository;


@Service
public class AsistenciaServiceImp implements IAsistencia{

    @Autowired
    public  AsistenciaRepository asistenciaRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;


    @Override
    public Asistencia crearAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

   

    @Override
    public List<Asistencia> mostrarAsistencias() {
        return asistenciaRepository.findAll();
    }
    
    @Override
    public Asistencia eliminarAsistencia(Long id) {
        Optional<Asistencia> asistencia= asistenciaRepository.findById(id);
        if(asistencia.isPresent()){
            asistencia.get().setUsuario(null);
            asistenciaRepository.delete(asistencia.get());
            return  asistencia.get();
        }
        return null;
    }

    @Override
    public List<Asistencia> buscarAsistenciaPorIdUsuario(Long idUser) {
       
        Optional<User> usuario = usuarioRepository.findById(idUser);
        if(usuario.isPresent()){
            return asistenciaRepository.findByUsuarioId(idUser);
        }

        return null;
    }
}
