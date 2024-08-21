package com.java.desktopApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "fase_rol_requisitos")
public class FaseRequisito {
    @Id
    private Long id;
    private Long rol_id,requisito_id,fase_id;

}
