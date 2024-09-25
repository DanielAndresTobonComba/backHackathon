package com.artgallery.artgallery.estado.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artgallery.artgallery.estado.application.IEstado;
import com.artgallery.artgallery.estado.domain.Estado;

@Service
public class EstadoServiceImp implements IEstado{

    @Autowired
    public EstadoRespository estadoRepository;



    @Override
    public Estado crearEstado(Estado estado) {
        return estadoRepository.save(estado);
    }


    @Override
    public Optional<Estado> buscarEstadoPorId(Long cedula) {
        return estadoRepository.findById(cedula);
    }   

    @Override
    public List<Estado> mostrarEstados() {
        return estadoRepository.findAll();
    }


    @Override
    public Estado eliminarEstado(Long id) {
        Optional<Estado> estado= estadoRepository.findById(id);
        if(estado.isPresent()){
            estadoRepository.delete(estado.get());
            return  estado.get();
        }
        return null;
    }


    @Override
    public Estado actualizarEstado( Estado estado) {
        return estadoRepository.save(estado);
    }
    
}
