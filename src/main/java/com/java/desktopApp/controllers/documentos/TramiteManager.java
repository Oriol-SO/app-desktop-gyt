package com.java.desktopApp.controllers.documentos;

import com.java.desktopApp.entities.Integrante;
import com.java.desktopApp.services.DTO.PersonaDTO;
import com.java.desktopApp.services.IntegranteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TramiteManager {
    @Autowired
    private IntegranteService integranteService;


    public PersonaDTO searchTramite(String codigo){
        return integranteService.search_integrante(codigo);
    }
}
