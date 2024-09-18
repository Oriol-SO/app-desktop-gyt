package com.java.desktopApp.controllers.documentos.file;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.services.DTO.FileTDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ItemFile {

    private FileTDO file;

    @FXML
    private Label labelFecha,labelEstado;

    @FXML
    public void botonIr(ActionEvent event) throws IOException {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("file dialog");

        ViewManager vista=ViewManager.loadNewVista();
        vista.loadfxml("/views/documentos/file/viewfile.fxml");
        vista.addLoaderContext();
        Parent view=vista.getLoader().load();

        ViewFile viewFile=vista.getLoader().getController();
        viewFile.setInfo(file);

        dialog.getDialogPane().setContent(view);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        dialog.showAndWait();
    }


    //private final String urlpage="https://jovial-shannon.209-126-85-157.plesk.page" ;
    private final String urlpage="https://gradostitulos.undac.edu.pe" ;
    @FXML
    public void botonCopiar(ActionEvent event){
        String urlToCopy = urlpage+file.getPath();

        // Crear un ClipboardContent y añadir la URL
        ClipboardContent content = new ClipboardContent();
        content.putString(urlToCopy);

        // Obtener el portapapeles del sistema y colocar el contenido
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(content);
    }

    public void setInfo(FileTDO doc){
        file=doc;
        labelFecha.setText("Actualizado: "+doc.getFecha().toString());
        labelEstado.setText(doc.getEstado()?"Activo":"eliminado");
    }
}
