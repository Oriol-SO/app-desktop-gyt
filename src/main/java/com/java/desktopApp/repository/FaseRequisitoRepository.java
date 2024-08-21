package com.java.desktopApp.repository;

import com.java.desktopApp.entities.FaseRequisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaseRequisitoRepository extends JpaRepository<FaseRequisito,Long> {

    @Query("SELECT f FROM FaseRequisito f WHERE f.requisito_id IS NOT NULL AND f.fase_id= :id")
    List<FaseRequisito> findByFase(Long id);

    @Query("SELECT f.id,r.nombre  FROM FaseRequisito f JOIN Requisito r ON r.id=f.requisito_id WHERE f.fase_id = :id")
    List<Object[]> findByFaseWhitrequisito(Long id);


}
