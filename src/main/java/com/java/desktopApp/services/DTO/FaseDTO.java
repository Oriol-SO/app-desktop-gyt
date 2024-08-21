package com.java.desktopApp.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaseDTO {
    private Long id;

    private String nombre;
    private int numero;
    private Long proceso_id;
    private Long encargado_ejecutar,encargado_revisar;


    //otros atributos de las fases
}
