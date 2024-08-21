package com.java.desktopApp.services;

import com.java.desktopApp.entities.Fase;
import com.java.desktopApp.repository.FaseRepository;
import com.java.desktopApp.services.DTO.FaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaseService {

    @Autowired
    private FaseRepository faseRepository;

    public List<FaseDTO> getFasesFromProceso(Long id){
        List<Fase> lista=faseRepository.findByProceso(id);

        List<FaseDTO> faseslist=lista.stream()
                .map(fase->FaseDTO.builder()
                        .proceso_id(fase.getProceso_id())
                        .id(fase.getId())
                        .nombre(fase.getNombre())
                        .encargado_revisar(fase.getEncargado_revisar())
                        .encargado_ejecutar(fase.getEncargado_ejecutar())
                        .numero(fase.getNumero())
                        .build()
        ).toList();
        return faseslist;
    }
}
