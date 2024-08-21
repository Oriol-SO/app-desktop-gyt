package com.java.desktopApp.repository;

import com.java.desktopApp.entities.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito,Long> {
}
