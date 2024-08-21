package com.java.desktopApp.services;

import com.java.desktopApp.models.Dashboard;
import com.java.desktopApp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Long getNumeroPersonas(){
        return personaRepository.count();
    }
}
