package com.java.desktopApp.controllers.dashboard;

import com.java.desktopApp.models.Dashboard;
import com.java.desktopApp.services.PersonaService;
import com.java.desktopApp.services.TramiteService;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

public class DashboardInfo {

    @Autowired
    private TramiteService tramiteService;
    @Autowired
    private PersonaService personaService;

    private Label labelnumtramites,labelnumusuarios;

    public void setServices(TramiteService tramService, PersonaService perService){
        tramiteService=tramService;
        personaService=perService;
    }

    public void setLabelnumtramites(Label labelnumtramites) {
        this.labelnumtramites = labelnumtramites;
    }

    public void setLabelnumusuarios(Label labelnumusuarios) {
        this.labelnumusuarios = labelnumusuarios;
    }

    public void setDashboardInfo(){
        Long numerotramites= Dashboard.getDash().getNum_tramites();
        Long numerousuarios=Dashboard.getDash().getNum_registros();

        if(numerotramites==null){
            Dashboard.getDash().setNum_tramites(tramiteService.getNumeroTramites());
            numerotramites=Dashboard.getDash().getNum_tramites();
        }
        if(numerousuarios==null){
            Dashboard.getDash().setNum_registros(personaService.getNumeroPersonas());
            numerousuarios=Dashboard.getDash().getNum_registros();
        }
        labelnumtramites.setText(numerotramites.toString());
        labelnumusuarios.setText(numerousuarios.toString());
    }
}
