package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "revisiones")
public class Aprobado {
    @Id
    private Long id;
    private Long file_id;

    private Timestamp created_at;
}
