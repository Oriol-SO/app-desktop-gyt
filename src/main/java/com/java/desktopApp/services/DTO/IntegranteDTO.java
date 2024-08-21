package com.java.desktopApp.services.DTO;

import com.java.desktopApp.entities.Persona;
import com.java.desktopApp.entities.Tramite;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IntegranteDTO {

    private Long id;
    private int fase_actual;
    private String codigo;
    private boolean uso;
    private boolean estado;
    private Date fec_inicio;

    private TramiteDTO tramite;
    private PersonaDTO persona;

    private List<TiempoDTO> listTiempos=new ArrayList<>();
}
