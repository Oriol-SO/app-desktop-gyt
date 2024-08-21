package com.java.desktopApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString//(exclude = {"persona","trmaite"})
@EqualsAndHashCode//(exclude = {"persona","tramite"})
@Entity
@Table(name="tramite_integrantes")
public class Integrante {
    @Id
    private Long id;
    private Long tramite_id;
    private Long persona_id;
    private int fase_actual;
    private String codigo;
    private boolean uso;
    private boolean estado;
    private Date fec_inicio;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;*/
}
