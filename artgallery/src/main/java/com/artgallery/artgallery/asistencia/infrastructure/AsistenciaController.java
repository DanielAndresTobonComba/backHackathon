package com.artgallery.artgallery.asistencia.infrastructure;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.asistencia.domain.Asistencia;
import com.artgallery.artgallery.asistencia.domain.AsistenciaDTO;
import com.artgallery.artgallery.estado.domain.Estado;
import com.artgallery.artgallery.estado.domain.EstadoDTO;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioImplement;
import com.artgallery.artgallery.utils.FieldValidation;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/asistencia")
public class AsistenciaController {



    @Autowired
    public AsistenciaServiceImp asistenciaServiceImp;


    @Autowired
    public UsuarioImplement usuarioImplement;

     @PostMapping("")
    public ResponseEntity<?> crearAsistencia(@Valid @RequestBody AsistenciaDTO asistenciaDTO, BindingResult result){
         if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }

        User usuario =  usuarioImplement.buscarUsuarioPorId(asistenciaDTO.getUsuarioId());
        if(usuario == null){
            ResponseEntity.notFound().build();
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setFechaEntrada(asistenciaDTO.getFechaEntrada());
        asistencia.setFechaSalida(asistenciaDTO.getFechaSalida());
        asistencia.setUsuario(usuario);

        return ResponseEntity.ok().body(asistenciaServiceImp.crearAsistencia(asistencia));
    }

    @GetMapping("usuario/{userId}")
    public ResponseEntity<?> BuscarAsitenciaPorIdUser(@PathVariable Long userId) {
        List<Asistencia> listaAsistencias = asistenciaServiceImp.buscarAsistenciaPorIdUsuario(userId);

        if(listaAsistencias == null){
            return ResponseEntity.noContent().build();
        }

        return  ResponseEntity.ok().body(listaAsistencias);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsistencia(@PathVariable Long id){
        Asistencia asistencia = asistenciaServiceImp.eliminarAsistencia(id);
        if(asistencia==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(asistencia);
    }

}
