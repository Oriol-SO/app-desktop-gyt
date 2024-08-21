package com.java.desktopApp.models;

import com.java.desktopApp.entities.Integrante;
import lombok.Data;

@Data
public class IntegranteModel extends Integrante {

    //relaciones
    private PersonaModel persona;

    private TramiteModel tramite;

    public IntegranteModel(Integrante inte){
        this.setId(inte.getId());
        this.setEstado(inte.isEstado());
        this.setUso(inte.isUso());
        this.setCodigo(inte.getCodigo());
        this.setFase_actual(inte.getFase_actual());
       // this.setTramite_id(inte.getTramite_id());
    }
}
