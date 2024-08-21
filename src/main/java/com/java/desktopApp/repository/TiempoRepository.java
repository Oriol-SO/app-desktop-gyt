package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TiempoRepository extends JpaRepository<Tiempo,Long> {
    @Query("SELECT t FROM Tiempo t WHERE t.integrante_id = :id")
    List<Tiempo> findByIntegrante(Long id);
}
