package com.java.desktopApp.services;

import com.java.desktopApp.entities.Requisito;
import com.java.desktopApp.repository.FaseRequisitoRepository;
import com.java.desktopApp.repository.RequisitoRepository;
import com.java.desktopApp.services.DTO.FaseRequisitoTDO;
import com.java.desktopApp.services.DTO.RequisitoTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FaseRequisitoService {

    @Autowired
    private FaseRequisitoRepository faseRequisitoRepository;
    @Autowired
    private RequisitoRepository requisitoRepository;

    public List<FaseRequisitoTDO> getRequisitosFase(Long fase_id){
        List<FaseRequisitoTDO> listRequisitos=faseRequisitoRepository.findByFase(fase_id).stream().map(
                fasereq->FaseRequisitoTDO.builder()
                        .id(fasereq.getId())
                        .requisito(getRequisito(fasereq.getRequisito_id()))
                        .build()
        ).toList();
        return listRequisitos;
    }

    private RequisitoTDO getRequisito(Long id){
        Optional<Requisito> Opreq=requisitoRepository.findById(id);

        if (Opreq.isPresent()){
            Requisito req=Opreq.get();
            RequisitoTDO requisito=RequisitoTDO.builder()
                    .id(req.getId())
                    .nombre(req.getNombre())
                    .build();
            return requisito;
        }
        return  new RequisitoTDO();
    }

    public List<FaseRequisitoTDO> getRequisitosFase__(Long  id){
        List<FaseRequisitoTDO> faserequisitolist=new ArrayList<>();
        for (Object[] result: faseRequisitoRepository.findByFaseWhitrequisito(id)){
            Long reqid=(Long) result[0];
            String nombre=(String) result[1];
            faserequisitolist.add(
                    FaseRequisitoTDO.builder()
                            .id(reqid)
                            .nombre(nombre)
                            .build()
            );
        }
        return faserequisitolist;
    }
}
