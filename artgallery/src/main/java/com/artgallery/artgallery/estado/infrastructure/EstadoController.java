package com.artgallery.artgallery.estado.infrastructure;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artgallery.artgallery.estado.domain.Estado;
import com.artgallery.artgallery.estado.domain.EstadoDTO;
import com.artgallery.artgallery.utils.FieldValidation;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    
    @Autowired
    public EstadoServiceImp estadoServiceImp;


    @PostMapping("")
    public ResponseEntity<?> crearEstado(@Valid @RequestBody Estado estado, BindingResult result){
         if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }
        return ResponseEntity.ok().body(estadoServiceImp.crearEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarEstadoPorId(@PathVariable Long id) {
        Optional<Estado> estado = estadoServiceImp.buscarEstadoPorId(id);
        if (estado.isPresent()) {
            return ResponseEntity.ok().body(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<?> mostrarEstados() {
        List<Estado> listaEstados = estadoServiceImp.mostrarEstados();
        return ResponseEntity.ok().body(listaEstados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstado(@PathVariable Long id){
        Estado estado = estadoServiceImp.eliminarEstado(id);
        if(estado==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @Valid  @RequestBody EstadoDTO estadoDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return FieldValidation.validation(result);
        }
        Optional<Estado> estadoop = estadoServiceImp.buscarEstadoPorId(id);
        if(estadoop.isPresent()){
            Estado estado = estadoop.get();
            estado.setNombre(estadoDTO.getNombre());
            estadoServiceImp.actualizarEstado(estado);
            return ResponseEntity.ok().body(estado);
        }
        return ResponseEntity.notFound().build();
    }

    





    


    
    

}
