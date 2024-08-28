package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Observado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObservadoRepository  extends JpaRepository<Observado,Long> {

    @Query("SELECT o FROM Observado o WHERE o.file_id= :file_id")
    Optional<Observado> findByFile(Long file_id);
}
