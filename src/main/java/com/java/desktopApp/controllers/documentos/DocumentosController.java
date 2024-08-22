package com.java.desktopApp.controllers.documentos;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.controllers.component.ViewPersona;
import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.PersonaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class DocumentosController implements Initializable {

    @Autowired
    private TramiteManager tramiteManager;

     @FXML
     private TextField txtCodigo;

     @FXML
     private Pane panelresult;

     @FXML
     private VBox paneInfoTramite;

     @FXML
     private VBox panellisttramites;

     @FXML
     private Label labelError;

     @FXML
     public void btnBuscar(ActionEvent actionEvent) throws IOException {
         try{
             labelError.setText("");
             panelresult.getChildren().clear();
             panellisttramites.getChildren().clear();

             PersonaDTO inte=tramiteManager.searchTramite(txtCodigo.getText());
             if(inte != null){
                 addPersonaInfo("/views/components/viewpersona.fxml",inte);
                 addListaTramites(inte.getListIntegrantes());
             }else{
                 throw new AppException("No se encontraron registros");
             }
         }catch(AppException error){
             labelError.setText(error.getMessage());
         }

     }

    private void addPersonaInfo(String pathfxml,PersonaDTO persona) throws IOException {
        ViewManager vista= ViewManager.loadVista();
        vista.loadfxml(pathfxml);
        vista.addLoaderContext();
        Parent view=vista.getLoader().load();

        ViewPersona vistaPersona=vista.getLoader().getController();
        vistaPersona.setInfo(persona);

        panelresult.getChildren().add(view);
    }

    private void addListaTramites(List<IntegranteDTO> info) throws IOException {
        ListTramites lista= new ListTramites();
        lista.setBoxlisttramites(panellisttramites,paneInfoTramite);
        lista.setInfo(info);

     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("PANEL DE DOCUMENTOS");
    }
}
