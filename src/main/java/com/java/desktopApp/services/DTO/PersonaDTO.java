package com.java.desktopApp.services.DTO;

import com.java.desktopApp.entities.Integrante;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDTO {
    private Long id;
    private String nom,apePat,apeMat,numDoc,email,cod_alum,numcel,fac_nom,esc_nom,especialidad,sede;

    private List<IntegranteDTO> listIntegrantes= new ArrayList<>();
}
