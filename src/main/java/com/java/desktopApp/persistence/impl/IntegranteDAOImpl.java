package com.java.desktopApp.persistence.impl;

import com.java.desktopApp.entities.Integrante;
import com.java.desktopApp.persistence.IntegranteDAO;
import com.java.desktopApp.repository.IntegranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IntegranteDAOImpl implements IntegranteDAO {

     @Autowired
     private IntegranteRepository integranteRepository;

    @Override
    public Optional<Integrante> findById(Long id) {
        return integranteRepository.findById(id);
    }

    @Override
    public List<Integrante> findByCodigo(String codigo) {
        return integranteRepository.findByCodigo(codigo);
    }

    @Override
    public List<Integrante> findAll() {
        return integranteRepository.findAll();
    }
}
