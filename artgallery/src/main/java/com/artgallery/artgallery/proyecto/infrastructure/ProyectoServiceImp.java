package com.artgallery.artgallery.proyecto.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.proyecto.application.IProyecto;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProyectoServiceImp implements IProyecto{

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Proyecto crearProyecto(Proyecto proyecto) {
        return  proyectoRepository.save(proyecto);
    }


    @Override
    public Proyecto buscarProyectoPorId(Long id) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(id);
        if (!proyecto.isPresent()) {
            throw new EntityNotFoundException("proyecto no encontrado con id: " + id);
        }
        return proyecto.get();
    }

    @Override
    public List<Proyecto> mostrarProyectos() {
        return proyectoRepository.findAll();
    }

    @Override
    public void eliminarProyecto(Long id) {
      
        Proyecto proyecto = proyectoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con id: " + id));

        for (User user : proyecto.getUsuarios()) {
            user.getProyectos().remove(proyecto);
        }   
        proyecto.getUsuarios().clear();
        proyectoRepository.deleteById(id);
    }


    @Override
    public User asignarUsuarioaProyecto(String cedula, Long idProyecto) {
        Optional <Proyecto> proyecto = proyectoRepository.findById(idProyecto);
        Optional <User> usuario = usuarioRepository.findByCedula(cedula);
        if(proyecto.isPresent() &&  usuario.isPresent()){
            Proyecto proyecto2 = proyecto.get();
            proyecto2.getUsuarios().add(usuario.get());
            usuario.get().getProyectos().add(proyecto.get());
            proyectoRepository.save(proyecto2);
            return  usuario.get();
        }
        return  null;
    }

  
}
