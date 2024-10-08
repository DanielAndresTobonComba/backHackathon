package com.artgallery.artgallery.proyecto.infrastructure;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.estado.domain.Estado;
import com.artgallery.artgallery.estado.infrastructure.EstadoServiceImp;
import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.proyecto.domain.ProyectoDTO;
import com.artgallery.artgallery.proyecto.domain.UsuarioProyectoDTO;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioImplement;
import org.springframework.web.bind.annotation.PutMapping;


@RestController()
@RequestMapping("/api/proyecto")
@CrossOrigin(origins = "https://satisfied-rejoicing-production.up.railway.app", allowCredentials = "true")
public class ProyectoController {

    @Autowired
    private ProyectoServiceImp proyectoServiceImp;

    @Autowired
    private UsuarioImplement usuarioImplement;

    @Autowired
    private EstadoServiceImp estadoServiceImp;

    @PostMapping("/crear")
    public ResponseEntity<?> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setTechLead(proyecto.getTechLead());
        proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
        proyecto.setFechaFin(proyectoDTO.getFechaFin());
        proyecto.setHorasUsadas(proyectoDTO.getHorasUsadas());
       // TODO: handle exception

        User user = usuarioImplement.buscarUsuarioPorId(proyectoDTO.getIdLeader());
        if(user == null){
            ResponseEntity.badRequest().build();
        }
        proyecto.setTechLead(user);
        // Falta estado

        Optional <Estado> estado = estadoServiceImp.buscarEstadoPorId(proyectoDTO.getIdEstado());
        if(estado == null){
            ResponseEntity.badRequest().build();
        } else if (estado.isPresent()) {
            proyecto.setEstado(estado.get());
        }
        
        return ResponseEntity.ok().body(proyectoServiceImp.crearProyecto(proyecto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyectoPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(proyectoServiceImp.buscarProyectoPorId(id));
    }

    @GetMapping("")
    public ResponseEntity<?> mostrarProyectos() {
        return ResponseEntity.ok().body(proyectoServiceImp.mostrarProyectos());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> mostrarProyectosPorUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(proyectoServiceImp.mostrarProyectoPorUserId(userId));
    }

    @DeleteMapping("/{id}")    
    public void  eliminarUsuario(@PathVariable Long id){
        proyectoServiceImp.eliminarProyecto(id);
    }

    @PutMapping("/asignarProyectoAUsuario")
    public  ResponseEntity<?>asignarProyectoAUsuario(@RequestBody UsuarioProyectoDTO usuarioProyectoDTO ) {
        return  ResponseEntity.ok().body(proyectoServiceImp.asignarUsuarioaProyecto(usuarioProyectoDTO.getCedulaUsuario(), usuarioProyectoDTO.getIdProyecto()));
    }


    @GetMapping("/traerProyectoSegunEstato/{id}")
    public ResponseEntity<?> obtenerProjectoPorEstado(@PathVariable Long id) {
        return ResponseEntity.ok().body(proyectoServiceImp.mostratProyectoSegunId(id));
    }

}
