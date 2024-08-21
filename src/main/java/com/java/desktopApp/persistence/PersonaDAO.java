package com.java.desktopApp.persistence;

import com.java.desktopApp.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaDAO {
    List<Persona> findAll();

    Optional<Persona> findById(Long id);


}
