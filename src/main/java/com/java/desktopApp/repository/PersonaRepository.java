package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
     @Query("SELECT p FROM Persona p WHERE p.cod_alum= :codigo")
    Optional<Persona> findByCodigo(String codigo);
}
