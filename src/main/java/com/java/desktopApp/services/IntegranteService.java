package com.java.desktopApp.services;

import com.java.desktopApp.entities.Integrante;
import com.java.desktopApp.entities.Persona;
import com.java.desktopApp.repository.IntegranteRepository;
import com.java.desktopApp.repository.PersonaRepository;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.PersonaDTO;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IntegranteService {

    @Autowired
    private  IntegranteRepository integranteRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private IntegranteTiempoService integranteTiempoService;

    @Autowired
    private TramiteService tramiteService;

    private List<IntegranteDTO> list_integrantes_from_persona(Long persona_id){
          List<IntegranteDTO> integrantes=integranteRepository.findByPersonaId(persona_id).stream()
                .map(integrante->IntegranteDTO.builder()
                        .id(integrante.getId())
                        .fase_actual(integrante.getFase_actual())
                        .fec_inicio(integrante.getFec_inicio())
                        .uso(integrante.isUso())
                        .estado(integrante.isEstado())
                        .tramite_id(integrante.getTramite_id())
                        .tramite(tramiteService.get_tramite(integrante.getTramite_id()))
                        .build()

                ).toList();
          return integrantes;
    }

    public IntegranteDTO getTiemposFase(IntegranteDTO integrante){
        integrante.setListTiempos(integranteTiempoService.getTiemposIntegrante(integrante.getId()));
        return integrante;
    }


    public PersonaDTO search_integrante(String codigo){
        //buscamos a la persona
        Optional<Persona> pers=personaRepository.findByCodigo(codigo);
        if(pers.isPresent()){
            Persona per=pers.get();
            //ahora asignamos la lista de tramites
            PersonaDTO persona=PersonaDTO.builder()
                            .id(per.getId())
                            .nom(per.getNom()).apePat(per.getApePat()).apeMat(per.getApeMat())
                            .cod_alum(per.getCod_alum())
                            .email(per.getEmail())
                            .numcel(per.getNumcel())
                            .numDoc(per.getNumDoc())
                            .esc_nom(per.getEsc_nom())
                            .especialidad(per.getEspecialidad())
                            .listIntegrantes(list_integrantes_from_persona(per.getId()))
                            .build();
            return  persona;
        }
        return null;
    }


}
