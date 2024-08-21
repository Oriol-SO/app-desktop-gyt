package com.java.desktopApp.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TramiteDTO {

    private Long id;
    private String modo_obtencion;
    private String tipo_tramite;
    private int integrantes;
    private Long persona_id;
    private Long proceso_id;
    private  boolean emitido;
    private Timestamp fec_envio;

    private PersonaDTO persona;
    private List<IntegranteDTO> listIntegrantes = new ArrayList<>();

    private ProcesoDTO proceso;
    private List<FaseDTO> listFases=new ArrayList<>();
}
