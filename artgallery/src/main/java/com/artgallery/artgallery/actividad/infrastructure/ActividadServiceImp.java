package com.artgallery.artgallery.actividad.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.actividad.application.IActividad;
import com.artgallery.artgallery.actividad.domain.Actividad;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioRepository;

@Service
public class ActividadServiceImp implements IActividad{

    @Autowired
    private  ActividadRepository actividadRepository;

    @Autowired
    private  UsuarioRepository usuarioRepository;



    @Override
    public Actividad crearActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    @Override
    public Optional<Actividad> buscarActividadPorId(Long id) {
        return  actividadRepository.findById(id);
    }


    @Override
    public List<Actividad> mostrarActividades() {
        return actividadRepository.findAll();
    }

    @Override
    public Actividad eliminarActividad(Long id) {
          Optional<Actividad> actividad= actividadRepository.findById(id);
        if(actividad.isPresent()){
            Actividad Act = actividad.get();
            Act.setEstado(null);
            Act.setProyecto(null);
            Act.setUsuario(null);
            actividadRepository.delete(Act);
            return Act;
        }
        return null;
    }


    @Override
    public Actividad actualizarActividad(Actividad actividad) {
        return  actividadRepository.save(actividad);
    }

    // @Override
    // public User InsertarUserActividad(Long idUser) {
        
    // }

    @Override
    public List<Actividad> mostrarActividaesPorIdUser(Long userId) {

        Optional<User> usuario = usuarioRepository.findById(userId);
        if(usuario.isPresent()){
            return actividadRepository.findByUsuarioId(userId);
        }

        return null;

       
    }

    @Override
    public Actividad InsertarActividadAUsuario(Long idUser, Long idActividad) {
       Optional<User> usuario =  usuarioRepository.findById(idUser);
       Optional<Actividad> actividad = actividadRepository.findById(idActividad);
       if(usuario.isPresent() && actividad.isPresent()){
            Actividad actividad2 = actividad.get();
            actividad2.setUsuario(usuario.get());
            return actividad2;
       }
       return null;
    }
}


