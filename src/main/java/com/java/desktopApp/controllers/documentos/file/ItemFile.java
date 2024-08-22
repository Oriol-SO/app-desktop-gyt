package com.java.desktopApp.controllers.documentos.file;

import com.java.desktopApp.services.DTO.FileTDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class ItemFile {

    private FileTDO file;

    @FXML
    private Label labelFecha,labelEstado;

    @FXML
    public void botonIr(ActionEvent event){
        System.out.println(file);
    }

    public void setInfo(FileTDO doc){
        file=doc;
        labelFecha.setText("Actualizado: "+doc.getFecha().toString());
        labelEstado.setText(doc.getEstado()?"Activo":"eliminado");
    }
}
