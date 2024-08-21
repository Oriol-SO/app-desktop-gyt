package com.java.desktopApp.controllers.dashboard;

import com.java.desktopApp.services.PersonaService;
import com.java.desktopApp.services.TramiteService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @Autowired
    private TramiteService tramiteService;
    @Autowired
    private PersonaService personaService;

    private DashboardInfo dashboardinfo;

    @FXML
    private Label labelnumtramites,labelnumusuarios;

    private void setInfoDashboard(){
        dashboardinfo= new DashboardInfo();
        dashboardinfo.setServices(tramiteService,personaService);
        dashboardinfo.setLabelnumtramites(labelnumtramites);
        dashboardinfo.setLabelnumusuarios(labelnumusuarios);
        dashboardinfo.setDashboardInfo();
    }


    public void getListTramite(){

        // listtramites.setItems(FXCollections.observableArrayList(tramites));
        /*
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/views/home/item.fxml"));

                //give the items some effect
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("INICIO");
       // getListTramite();
    }
}
