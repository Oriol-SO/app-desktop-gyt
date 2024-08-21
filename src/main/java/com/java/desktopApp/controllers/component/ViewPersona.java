package com.java.desktopApp.controllers.component;

import com.java.desktopApp.services.DTO.PersonaDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class ViewPersona {

    @FXML
    private Label labelPersonaMombre,labelCodigo,labelEscuela;


    public void setInfo(PersonaDTO persona){
        labelPersonaMombre.setText(persona.getNom()+" "+persona.getApePat()+" "+persona.getApeMat());
        labelCodigo.setText("Cod: "+persona.getCod_alum());
        labelEscuela.setText("esc: "+(persona.getEspecialidad()==null?persona.getEsc_nom():persona.getEspecialidad()));

        System.out.println("DATOS PERSONALES-----------------------------------");
    }
}
