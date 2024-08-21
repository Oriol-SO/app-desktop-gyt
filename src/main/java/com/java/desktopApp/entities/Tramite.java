package com.java.desktopApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//@ToString(exclude = {"listIntegrantes"})
//@EqualsAndHashCode(exclude = {"listIntegrantes"})
@Entity
@Table(name = "tramites")
public  class Tramite {
    //P88pw84*h
    @Id
    private Long id;
    private String modo_obtencion;
    private String tipo_tramite;
    private int integrantes;
    private Long persona_id;
    private Long proceso_id;
    private boolean emitido;
    private Timestamp fec_envio;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramite_id")
    private List<Integrante> listIntegrantes= new ArrayList<>();*/
}