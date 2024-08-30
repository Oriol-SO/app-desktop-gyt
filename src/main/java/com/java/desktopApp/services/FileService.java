package com.java.desktopApp.services;

import com.java.desktopApp.entities.Aprobado;
import com.java.desktopApp.entities.File;
import com.java.desktopApp.entities.Observado;
import com.java.desktopApp.repository.AprobadoRepository;
import com.java.desktopApp.repository.FileRepository;
import com.java.desktopApp.repository.ObservadoRepository;
import com.java.desktopApp.services.DTO.AprobadoDTO;
import com.java.desktopApp.services.DTO.FileTDO;
import com.java.desktopApp.services.DTO.ObservadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private AprobadoRepository aprobadoRepository;

    @Autowired
    private ObservadoRepository observadoRepository;

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

    public FileTDO updatePathFile(FileTDO f,String path){
        Optional<File> doc=fileRepository.findById(f.getId());
        if(doc.isPresent()){
            File file=doc.get();
            file.setPath(path);
            fileRepository.save(file);
            f.setPath(path);
        }
        return f;
    }

    public AprobadoDTO getAprobado(Long file_id){
        Optional<Aprobado> aproOp=aprobadoRepository.findByFile(file_id);
        if(aproOp.isPresent()){
            Aprobado apro=aproOp.get();
            AprobadoDTO aprobado=AprobadoDTO.builder()
                    .file_id(apro.getFile_id())
                    .id(apro.getId())
                    .created_at(apro.getCreated_at())
                    .build();
            System.out.println(aprobado);
            return aprobado;
        }
        return null;
    }

    public ObservadoDTO getObservado(Long file_id){
        Optional<Observado> obsOp=observadoRepository.findByFile(file_id);
        if(obsOp.isPresent()){
            Observado obs=obsOp.get();
            ObservadoDTO observado=ObservadoDTO.builder()
                    .file_id(obs.getFile_id())
                    .id(obs.getId())
                    .texto(obs.getTexto())
                    .created_at(obs.getCreated_at())
                    .build();
            return observado;
        }
        return null;
    }

    public FileTDO addDateApro(FileTDO file){
        file.setAprobado(getAprobado(file.getId()));
        file.setCall_aprobado(true);
        return file;
    }

    public FileTDO addDateObs(FileTDO file){
        file.setObservado(getObservado(file.getId()));
        file.setCall_observado(true);
        return file;
    }

    public FileTDO updateFechaFile(FileTDO file, Timestamp fecha){
        File fil=fileRepository.findById(file.getId()).get();
        fil.setFecha(fecha);
        fileRepository.save(fil);

        file.setFecha(fecha);
        return file;
    }

    public FileTDO updateFechaApro(FileTDO file, Timestamp fecha){
        if(file.getAprobado()!=null) {
            Aprobado apro = new Aprobado();

            apro.setCreated_at(fecha);
            apro.setId(file.getAprobado().getId());
            apro.setFile_id(file.getId());

            aprobadoRepository.save(apro);
            file.getAprobado().setCreated_at(fecha);
        }
        return file;
    }
}
