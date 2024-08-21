package com.java.desktopApp.persistence;

import com.java.desktopApp.entities.Tramite;

import java.util.Optional;

public interface TramiteDAO {

    Optional<Tramite> findById(Long id);
}
