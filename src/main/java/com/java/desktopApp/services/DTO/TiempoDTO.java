package com.java.desktopApp.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TiempoDTO {

    private Long id;
    private Long tramite_id,integrante_id;
    private int numero_fase;
    private Timestamp fecha_inicio,fecha_fin;

}
