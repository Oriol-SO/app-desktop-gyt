package com.java.desktopApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@ToString//(exclude = {"listIntegrantes"})
@EqualsAndHashCode//(exclude = {"listIntegrantes"})
//@ToString
@Entity
@Table(name="personas")
public class Persona {

    @Id
    private Long id;
    private String nom,apePat,apeMat,numDoc,email,cod_alum,numcel,fac_nom,esc_nom,especialidad,sede;

    /*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private List<Integrante> listIntegrantes= new ArrayList<>();*/


}
