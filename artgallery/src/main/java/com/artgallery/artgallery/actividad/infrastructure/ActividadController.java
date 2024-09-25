package com.artgallery.artgallery.actividad.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.actividad.domain.Actividad;
import com.artgallery.artgallery.actividad.domain.ActividadDTO;
import com.artgallery.artgallery.actividad.domain.ActividadProyectoDTO;
import com.artgallery.artgallery.actividad.domain.ActividadUsuarioDTO;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.proyecto.infrastructure.ProyectoServiceImp;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/actividad")
public class ActividadController {

    @Autowired
    private ActividadServiceImp actividadServiceImp;


    @Autowired
    private ProyectoServiceImp proyectoServiceImp;


     @PostMapping("")
    public ResponseEntity<?> crearActividad(@Valid @RequestBody ActividadDTO actividadDTO, BindingResult result){
         if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }

        Actividad actividad = new Actividad();
        actividad.setNombre(actividadDTO.getNombre());
        actividad.setDescripcion(actividadDTO.getDescripcion());
        actividad.setFechaInicio(actividadDTO.getFechaInicio());
        actividad.setFechaFin(actividadDTO.getFechaFin());
        actividad.setHorasUsadas(actividad.getHorasUsadas());

        Proyecto proyecto = proyectoServiceImp.buscarProyectoPorId(actividadDTO.getIdProyecto());
        if(proyecto==null){
            return  new ResponseEntity<>("El proyecto no existe", HttpStatus.NOT_FOUND);
        }
        actividad.setProyecto(proyecto);
        return ResponseEntity.ok().body(actividadServiceImp.crearActividad(actividad));
    }
    
     @GetMapping("/{id}")
    public ResponseEntity<?> BuscarActividadPorId(@PathVariable Long id) {
        Optional<Actividad> actividad = actividadServiceImp.buscarActividadPorId(id);
        if (actividad.isPresent()) {
            return ResponseEntity.ok().body(actividad.get());
        }

        return ResponseEntity.notFound().build();
    }


     @GetMapping("")
    public ResponseEntity<?> mostrarActividades() {
        List<Actividad> listaActividad = actividadServiceImp.mostrarActividades();
        if(listaActividad==null){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(listaActividad);
    }


    @GetMapping("showUsersActivity/{userId}")
    public ResponseEntity<?> mostrarActividadesPorIdusuario(@PathVariable Long userId) {
        List<Actividad> listaActividad = actividadServiceImp.mostrarActividaesPorIdUser(userId);
        return ResponseEntity.ok().body(listaActividad);
    }


    @GetMapping("showProyectActivitys/{proyectId}")
    public ResponseEntity<?> mostrarActividadesPorIdProyecto(@PathVariable Long proyectId) {
        List<Actividad> listaActividad = actividadServiceImp.mostrarActividaesPorIdUser(proyectId);
        return ResponseEntity.ok().body(listaActividad);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarActividad(@PathVariable Long id){
        Actividad actividad = actividadServiceImp.eliminarActividad(id);
        if(actividad==null){
            actividad.setEstado(null);
            actividad.setProyecto(null);
            actividad.setUsuario(null);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(actividad);
    }

@PutMapping("")
public ResponseEntity<?> asignarUserActividad(@RequestBody ActividadUsuarioDTO actividadUsuarioDTO){
   Actividad actividad = actividadServiceImp.InsertarActividadAUsuario(actividadUsuarioDTO.getIdUser(), actividadUsuarioDTO.getIdActividad());
   if(actividad == null){
    ResponseEntity.internalServerError().build();
   }
    return ResponseEntity.ok().body(actividad);
}


@PutMapping("/proyecto")
public ResponseEntity<?> asignarActividadProyecto(@RequestBody ActividadProyectoDTO actividadProyectoDTO){
   Actividad actividad = actividadServiceImp.InsertarActividadAUsuario(actividadProyectoDTO.getIdActividad(), actividadProyectoDTO.getIdProyecto());
   if(actividad == null){
    ResponseEntity.internalServerError().build();
   }
    return ResponseEntity.ok().body(actividad);
}
    



@PutMapping("/actualizarActividad/{id}")
public ResponseEntity<?> actualizarActividad(@PathVariable Long id, @Valid @RequestBody Actividad actividadActualizada, BindingResult result) {

    if (result.hasFieldErrors()) {
        return FieldValidation.validation(result);
    }

    Optional<Actividad> actividadOp = actividadServiceImp.buscarActividadPorId(id);
    System.out.println("llegue a buscar la actividad");
    System.out.println(actividadOp);
    
    if (actividadOp.isPresent()) {
        Actividad actividadExistente = actividadOp.get();
        
        // Actualizando los campos de la actividad existente
        actividadExistente.setNombre(actividadActualizada.getNombre());
        actividadExistente.setDescripcion(actividadActualizada.getDescripcion());
        actividadExistente.setHorasUsadas(actividadActualizada.getHorasUsadas());
        actividadExistente.setFechaInicio(actividadActualizada.getFechaInicio());
        actividadExistente.setFechaFin(actividadActualizada.getFechaFin());

        // Actualizar el usuario, proyecto y estado solo si es necesario
        if (actividadActualizada.getUsuario() != null) {
            actividadExistente.setUsuario(actividadActualizada.getUsuario());
        }
        if (actividadActualizada.getProyecto() != null) {
            actividadExistente.setProyecto(actividadActualizada.getProyecto());
        }
        if (actividadActualizada.getEstado() != null) {
            actividadExistente.setEstado(actividadActualizada.getEstado());
        }

        // voy a la implementacion de la interfaz
        Actividad actividadGuardada = actividadServiceImp.actualizarActividad(actividadExistente);
        return ResponseEntity.ok().body(actividadGuardada);
    }

    return ResponseEntity.notFound().build();
}


}
