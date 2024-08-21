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
public class FaseRequisitoTDO {
    private Long id;
    private Long rol_id,requisito_id,fase_id;

    private String nombre;

    private RequisitoTDO requisito;
    //private List<FileTDO> files=new ArrayList<>();
}
