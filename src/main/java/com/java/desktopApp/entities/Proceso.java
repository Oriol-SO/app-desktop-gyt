package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "procesos")
public class Proceso {

    @Id
    private Long id;

    private String procNom,tipo;
    private Long grado_id,moda_id,obtencion_id,monto_id;
    private boolean estado,use_foto,pago_unique;
}