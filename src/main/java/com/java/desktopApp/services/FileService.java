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

    public List<FileTDO> getListDocumentosFase(Long fase_id,Long Integrante_id){
        //obtenemos los faserol_requisitos;
        return new ArrayList<>();
    }
}
