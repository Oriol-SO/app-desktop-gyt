package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "files")
public class File {

    @Id
    private Long id;
    private String path;
    private Timestamp fecha,updated_at;
    private boolean estado,voucher;
    private  Long tramite_id,integrante_id,faserolreq_id,solicitud_id;

}
