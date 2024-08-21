package com.java.desktopApp.persistence;

import com.java.desktopApp.entities.Integrante;

import java.util.List;
import java.util.Optional;

public interface IntegranteDAO {

    Optional<Integrante> findById(Long id);

    List<Integrante> findByCodigo(String codigo);

    List<Integrante> findAll();
}
