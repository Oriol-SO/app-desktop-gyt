package com.java.desktopApp.controllers.datos;

import com.java.desktopApp.models.Consulta;
import javafx.scene.control.ChoiceBox;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaManager {

    private List<Consulta> ListConsultas;

    public void AddConsultas(ChoiceBox lista){
        ListConsultas=new ArrayList<>();
        ListConsultas.add(new Consulta(1,"Obtener datos por consejo."));
        ListConsultas.add(new Consulta(2,"Obtener datos por facultad."));

        lista.getItems().addAll(ListConsultas);
    }

    public void GetConsulta(Consulta IdConsulta){
        System.out.println(IdConsulta.ConstruirConsulta("consejos"));
    }
}
