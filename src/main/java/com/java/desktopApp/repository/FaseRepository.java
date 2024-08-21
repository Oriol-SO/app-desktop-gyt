package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaseRepository extends JpaRepository<Fase,Long>  {
    @Query("SELECT f FROM Fase f WHERE f.proceso_id= :id")
    List<Fase> findByProceso(Long id);

}
