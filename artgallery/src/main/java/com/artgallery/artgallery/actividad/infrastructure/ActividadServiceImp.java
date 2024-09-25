package com.artgallery.artgallery.actividad.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.actividad.application.IActividad;
import com.artgallery.artgallery.actividad.domain.Actividad;

@Service
public class ActividadServiceImp implements IActividad{

    @Autowired
    private  ActividadRepository actividadRepository;



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
}


