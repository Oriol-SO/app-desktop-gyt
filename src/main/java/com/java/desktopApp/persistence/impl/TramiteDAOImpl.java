package com.java.desktopApp.persistence.impl;

import com.java.desktopApp.entities.Tramite;
import com.java.desktopApp.persistence.TramiteDAO;
import com.java.desktopApp.repository.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TramiteDAOImpl implements TramiteDAO {

     @Autowired
     private TramiteRepository tramiteRepository;

    @Override
    public Optional<Tramite> findById(Long id) {
        return tramiteRepository.findById(id);
    }
}
