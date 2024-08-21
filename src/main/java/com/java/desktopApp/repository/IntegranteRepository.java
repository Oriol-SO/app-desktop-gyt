package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante,Long> {

    List<Integrante> findByCodigo(String codigo);

    @Query("SELECT i FROM Integrante i WHERE i.persona_id = :id")
    List<Integrante> findByPersonaId(Long id);



}
