package com.java.desktopApp.controllers.documentos;

import com.java.desktopApp.config.ContextManager;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ListTramites  {


    private VBox boxlisttramites;
    private VBox paneInfoTramite;

    public void  setBoxlisttramites(VBox box, VBox panelinfotramite){
        boxlisttramites=box;
        paneInfoTramite=panelinfotramite;
    }

    public void setInfo( List<IntegranteDTO> integrantes) throws IOException {
        //System.out.println(persona.getListIntegrantes());
        ///renderizar la lista
        for(IntegranteDTO inte :integrantes){
            addTramite("/views/documentos/tramiteitem.fxml",inte);
        }
    }

    private void addTramite(String pathfxml, IntegranteDTO integrante) throws IOException {
        //crear nueva vista para cada item
        ContextManager context=ContextManager.getContexto();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathfxml));
        //loader.setControllerFactory(context.getContext()::getBean);
        Parent view =loader.load();

        ItemIntegrante tramite=loader.getController();
        tramite.setInfo(integrante,paneInfoTramite);

        boxlisttramites.getChildren().add(view);
    }


}
