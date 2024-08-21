package com.java.desktopApp.controllers.datos;

import com.java.desktopApp.models.Consulta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DatosController implements Initializable {

    @FXML
    private ChoiceBox<Consulta> idSelectConsulta;
    @FXML
    private TextField txtConsejo;

    @Autowired
    private ConsultaManager consultaManager;

    @FXML
    public void btnEnviarConsulta(ActionEvent actionEvent){
        consultaManager.GetConsulta(idSelectConsulta.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //agregar la lista de consultas al choiceBox;
        consultaManager.AddConsultas(idSelectConsulta);
    }
}
