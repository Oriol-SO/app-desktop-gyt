package com.java.desktopApp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "observaciones")
public class Observado {
    @Id
    private Long id;
    private Long file_id;
    private String texto;
    private Timestamp created_at;
}
