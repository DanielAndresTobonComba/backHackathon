package com.artgallery.artgallery.actividad.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.artgallery.artgallery.actividad.domain.ActividadUsuarioDTO;
import com.artgallery.artgallery.estado.infrastructure.EstadoServiceImp;
import com.artgallery.artgallery.proyecto.infrastructure.ProyectoServiceImp;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioImplement;
import com.artgallery.artgallery.utils.FieldValidation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/actividad")
public class ActividadController {

    @Autowired
    private ActividadServiceImp actividadServiceImp;

    @Autowired
    private UsuarioImplement usuarioImplement;

    @Autowired
    private ProyectoServiceImp proyectoServiceImp;

    @Autowired
    private EstadoServiceImp estadoServiceImp;



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
        return ResponseEntity.ok().body(listaActividad);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> mostrarActividadesPorIdusuario(@PathVariable Long userId) {
        List<Actividad> listaActividad = actividadServiceImp.mostrarActividaesPorIdUser(userId);
        if(listaActividad==null){
            ResponseEntity.notFound().build();

        }
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
    


    // @PutMapping("/{id}")
    // public ResponseEntity<?> actualizarActividad(@PathVariable Long id, @Valid  @RequestBody ActividadDTOActualizar actividadDTO, BindingResult result) {
    //     if (result.hasFieldErrors()) {
    //         return FieldValidation.validation(result);
    //     }
    //     Optional<Actividad> actividadOp = actividadServiceImp.buscarActividadPorId(id);
    //     if(actividadOp.isPresent()){
    //         Actividad actividad = actividadOp.get();
    //         actividad.setNombre(actividadDTO.getNombre());
    //         actividad.setDescripcion(actividadDTO.getDescripcion());
    //         actividad.setFechaInicio(actividadDTO.getFechaInicio());
    //         actividad.setFechaFin(actividadDTO.getFechaFin());
    //         actividad.setHorasUsadas(actividadDTO.getHorasUsadas());

    //         proyectoServiceImp.buscarProyectoPorId(actividadDTO.)
    //     }
    //     return ResponseEntity.notFound().build();
    // }

}
