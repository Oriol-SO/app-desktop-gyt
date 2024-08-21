package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "tramite_tiempos")
public class Tiempo {

    @Id
    private Long id;
    private Long tramite_id,integrante_id;
    private int numero_fase;
    private Timestamp fecha_inicio,fecha_fin;


}
