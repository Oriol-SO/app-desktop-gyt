package com.java.desktopApp.services;

import com.java.desktopApp.repository.TiempoRepository;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.TiempoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteTiempoService {

    @Autowired
    private TiempoRepository tiempoRepository;

    public List<TiempoDTO> getTiemposIntegrante( Long id){
        List<TiempoDTO>listTiempos= tiempoRepository.findByIntegrante(id).stream()
                .map(tiempo->TiempoDTO.builder()
                        .integrante_id(tiempo.getIntegrante_id())
                        .tramite_id(tiempo.getTramite_id())
                        .id(tiempo.getId())
                        .fecha_inicio(tiempo.getFecha_inicio())
                        .fecha_fin(tiempo.getFecha_fin())
                        .numero_fase(tiempo.getNumero_fase())
                        .build()
                ).toList();
        return listTiempos;
    }



}
