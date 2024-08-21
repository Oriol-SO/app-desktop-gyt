package com.java.desktopApp.services;

import com.java.desktopApp.entities.Tramite;
import com.java.desktopApp.repository.ProcesoRepository;
import com.java.desktopApp.repository.TramiteRepository;
import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.ProcesoDTO;
import com.java.desktopApp.services.DTO.TramiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TramiteService {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private FaseService faseService;

    public List<Tramite> getTramites(int limit){
        Page<Tramite> page = tramiteRepository.findTop5ByOrderByIdDesc(PageRequest.of(0, limit));
        return page.getContent();
    }

    public Long getNumeroTramites(){
        //Dashboard.getDash().setNum_tramites(tramiteRepository.count());
        return tramiteRepository.count();
    }

    public TramiteDTO get_tramite(Long id){
        Optional<Tramite> tramOp=tramiteRepository.findById(id);

        if(tramOp.isPresent()){
            Tramite tram=tramOp.get();
            TramiteDTO tramite= TramiteDTO.builder()
                    .id(tram.getId())
                    .tipo_tramite(tram.getTipo_tramite())
                    .integrantes(tram.getIntegrantes())
                    .modo_obtencion(tram.getModo_obtencion())
                    .proceso_id(tram.getProceso_id())
                    .emitido(tram.isEmitido())
                    .fec_envio(tram.getFec_envio())
                    .build();

            return tramite;
        }
        return null;
    }

    public TramiteDTO get_fases(TramiteDTO tramite){
        tramite.setListFases(faseService.getFasesFromProceso(tramite.getProceso_id()));
        return  tramite;
    }
}
