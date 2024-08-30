package com.java.desktopApp.controllers.dashboard;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.services.PersonaService;
import com.java.desktopApp.services.TramiteService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class DashboardController{

    @Autowired
    private TramiteService tramiteService;
    @Autowired
    private PersonaService personaService;

    private DashboardInfo dashboardinfo;

    @FXML
    private Label labelnumtramites,labelnumusuarios;

    @FXML
    private Button btnTramites,btnDatos;

    private StackPane panelshow;

    private void setInfoDashboard(){
        dashboardinfo= new DashboardInfo();
        dashboardinfo.setServices(tramiteService,personaService);
        dashboardinfo.setLabelnumtramites(labelnumtramites);
        dashboardinfo.setLabelnumusuarios(labelnumusuarios);
        dashboardinfo.setDashboardInfo();
    }

    public void setPanel(StackPane panel){
        panelshow=panel;
    }

    @FXML
    public void handleClick(ActionEvent event) throws IOException {
        if (event.getSource() == btnTramites) {
            //setStyleButton(btnInicio,true);
            //CAMBIAR AL INICIO
            changuePanel("/views/documentos/gestiondocumentos.fxml");
        } else if (event.getSource()==btnDatos) {
            changuePanel("/views/datos/datos.fxml");
        }
    }

    private void changuePanel(String pathfxml) throws IOException {
        ViewManager vista= ViewManager.loadVista();
        vista.loadfxml(pathfxml);
        vista.addLoaderContext();
        Parent viewinicio=vista.getLoader().load();
        panelshow.getChildren().clear();
        panelshow.getChildren().setAll(viewinicio);
    }

}
