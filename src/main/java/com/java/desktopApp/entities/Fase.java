package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "fases")
public class Fase {

    @Id
    private Long id;

    private String nombre;
    private int numero;
    private Long proceso_id;
    private Long encargado_ejecutar,encargado_revisar;
}
