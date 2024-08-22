package com.java.desktopApp.controllers.documentos.file;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.entities.File;
import com.java.desktopApp.services.DTO.FaseRequisitoTDO;
import com.java.desktopApp.services.DTO.FileTDO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.FileService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ListFile implements Initializable {

    @FXML
    private VBox boxListFiles;

    @Autowired
    private FileService fileService;

    private List<FileTDO> fileList;

    public void setInfo(FaseRequisitoTDO req,IntegranteDTO inte) throws IOException {
            fileList=fileService.getListDocumentosReq(req.getId(),inte.getTramite().getId());
            for (FileTDO file: fileList){
                ViewManager vista=ViewManager.loadNewVista();
                vista.loadfxml("/views/documentos/file/itemfile.fxml");
                //vista.addLoaderContext();
                Parent view=vista.getLoader().load();

                ItemFile files= vista.getLoader().getController();
                files.setInfo(file);

                boxListFiles.getChildren().add(view);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
