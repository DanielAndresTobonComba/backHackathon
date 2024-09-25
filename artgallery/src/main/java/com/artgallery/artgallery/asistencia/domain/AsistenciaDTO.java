package com.artgallery.artgallery.asistencia.domain;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
public class AsistenciaDTO {
    private Date fechaEntrada;

    private Date fechaSalida;

    private Long usuarioId;
    
}
