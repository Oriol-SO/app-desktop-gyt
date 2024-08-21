package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Tramite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite,Long> {

    Page <Tramite> findTop5ByOrderByIdDesc(Pageable pageable);
}
