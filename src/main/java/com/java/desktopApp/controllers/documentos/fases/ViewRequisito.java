package com.java.desktopApp.controllers.documentos.fases;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.controllers.documentos.file.ListFile;
import com.java.desktopApp.services.DTO.FaseRequisitoTDO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewRequisito {

    @FXML
    private Label labelNombre;
    @FXML
    private VBox boxFiles;

    private FaseRequisitoTDO req;
    private IntegranteDTO inte;


    public void setInfo(FaseRequisitoTDO requisito, IntegranteDTO integrante,int i){
        this.req=requisito;
        this.inte=integrante;
        labelNombre.setText(i+". "+requisito.getNombre());
    }

    @FXML
    public void BtnFiles(ActionEvent event) throws IOException {
        //limpiar la lista
        boxFiles.getChildren().clear();
        ViewManager vista=ViewManager.loadNewVista();
        vista.loadfxml("/views/documentos/file/listfiles.fxml");
        vista.addLoaderContext();
        Parent view=vista.getLoader().load();

        ListFile files= vista.getLoader().getController();
        files.setInfo(this.req,this.inte);
        boxFiles.getChildren().add(view);
    }
}
