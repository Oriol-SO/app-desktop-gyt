package com.java.desktopApp.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileTDO {
    private Long id;
    private String path;
    private Timestamp fecha,updated_at;
    private Boolean estado,voucher;
    private  Long tramite_id,integrante_id,faserolreq_id,solicitud_id;

    private FaseRequisitoTDO requisito;
}
