package com.java.desktopApp.services;

import com.java.desktopApp.repository.FileRepository;
import com.java.desktopApp.services.DTO.FileTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public List<FileTDO> getListDocumentosReq(Long faserolreq_id,Long tramite_id){
        List<FileTDO> files=fileRepository.findByTramiteAndReq(faserolreq_id,tramite_id).stream().map(
                file -> FileTDO.builder()
                        .id(file.getId())
                        .path(file.getPath())
                        .fecha(file.getFecha())
                        .estado(file.getEstado())
                        .tramite_id(file.getTramite_id())
                        .integrante_id(file.getIntegrante_id())
                        .faserolreq_id(file.getFaserolreq_id())
                        .solicitud_id(file.getSolicitud_id())
                        .voucher(file.getVoucher())
                        .build()
        ).toList();

        return files;
    }

}
