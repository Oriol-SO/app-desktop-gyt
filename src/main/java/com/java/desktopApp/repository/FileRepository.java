package com.java.desktopApp.repository;

import com.java.desktopApp.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    @Query("SELECT f FROM File f where f.faserolreq_id= :faserolreq_id AND f.tramite_id= :tramite_id")
    List<File> findByTramiteAndReq(Long faserolreq_id, Long tramite_id);

}
