package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Aprobado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AprobadoRepository extends JpaRepository<Aprobado,Long> {

    @Query("SELECT a FROM Aprobado a WHERE a.file_id= :file_id")
    Optional<Aprobado> findByFile(Long file_id);
}
