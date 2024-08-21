package com.java.desktopApp.services;

import com.java.desktopApp.entities.Proceso;
import com.java.desktopApp.repository.ProcesoRepository;
import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.ProcesoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesoService {

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private FaseService faseService;

    public ProcesoDTO findProceso(Long id){
        Optional<Proceso> process=procesoRepository.findById(id);
        if(process.isPresent()){
            Proceso proc=process.get();
            ProcesoDTO proceso=ProcesoDTO.builder()
                    .id(proc.getId())
                    .estado(proc.isEstado())
                    .moda_id(proc.getModa_id())
                    .procNom(proc.getProcNom())
                    .obtencion_id(proc.getObtencion_id())
                    .pago_unique(proc.isPago_unique())
                    .use_foto(proc.isUse_foto())
                    .build();

            return proceso;
        }
        return null;
    }

    public ProcesoDTO getFases(ProcesoDTO proceso){
        List<FaseDTO> fases=faseService.getFasesFromProceso(proceso.getId());
        proceso.setListFases(fases);
        return proceso;
    }
}
