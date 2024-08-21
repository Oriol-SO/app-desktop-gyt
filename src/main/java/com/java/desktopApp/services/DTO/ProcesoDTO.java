package com.java.desktopApp.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcesoDTO {

    private Long id;
    private String procNom,tipo;
    private Long grado_id,moda_id,obtencion_id,monto_id;
    private boolean estado,use_foto,pago_unique;

    private List<FaseDTO> listFases= new ArrayList<>();
}
