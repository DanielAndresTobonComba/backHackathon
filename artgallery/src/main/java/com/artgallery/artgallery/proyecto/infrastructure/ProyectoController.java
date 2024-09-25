package com.artgallery.artgallery.proyecto.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.proyecto.domain.Proyecto;
import com.artgallery.artgallery.proyecto.domain.ProyectoDTO;
import com.artgallery.artgallery.proyecto.domain.UsuarioProyectoDTO;
import com.artgallery.artgallery.usuario.domain.User;
import com.artgallery.artgallery.usuario.infraestructure.UsuarioImplement;
import org.springframework.web.bind.annotation.PutMapping;


@RestController()
@RequestMapping("/api/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoServiceImp proyectoServiceImp;

    @Autowired
    private UsuarioImplement usuarioImplement;





    @PostMapping("")
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

    @GetMapping("/{userId}")
    public ResponseEntity<?> mostrarProyectosPorUserId(@PathVariable Long idUser) {
        return ResponseEntity.ok().body(proyectoServiceImp.mostrarProyectoPorUserId(idUser));
    }

    @DeleteMapping("/{id}")    
    public void  eliminarUsuario(@PathVariable Long id){
        proyectoServiceImp.eliminarProyecto(id);
    }

    @PutMapping("")
    public  ResponseEntity<?>asignarProyectoAUsuario(@RequestBody UsuarioProyectoDTO usuarioProyectoDTO ) {
        return  ResponseEntity.ok().body(proyectoServiceImp.asignarUsuarioaProyecto(usuarioProyectoDTO.getCedulaUsuario(), usuarioProyectoDTO.getIdProyecto()));
    }

}
