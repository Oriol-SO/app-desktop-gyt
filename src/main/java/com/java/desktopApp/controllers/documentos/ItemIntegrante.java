package com.java.desktopApp.controllers.documentos;

import com.java.desktopApp.config.ContextManager;
import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.repository.PersonaRepository;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ItemIntegrante {




    @FXML
    private Label labeltipoTramite,labelEstado,labelFechaInicio;

    private Pane panelDetailsTramite;

    private IntegranteDTO integrante;

    public void setInfo(IntegranteDTO integranteDTO, Pane panelInfoIntegrante){
        this.integrante=integranteDTO;
        this.panelDetailsTramite=panelInfoIntegrante;

        labeltipoTramite.setText(integranteDTO.getTramite().getTipo_tramite());
        if (integranteDTO.isEstado()){
            labelEstado.setText("Finalizado");
            labelEstado.setStyle("-fx-text-fill:green");
        } else if (integranteDTO.isUso()) {
            labelEstado.setText("En curso");
            labelEstado.setStyle("-fx-text-fill:blue");
        }else{
            labelEstado.setText("Retirado");
            labelEstado.setStyle("-fx-text-fill:#e10101");
        }
        labelFechaInicio.setText(integranteDTO.getFec_inicio().toString());
    }

    @FXML
    public void openTramite(ActionEvent event) throws IOException {
        //cargamos la vista para los detalles del tramite;
        //limpiamos el panel
        panelDetailsTramite.getChildren().clear();

        ViewManager vista= ViewManager.loadNewVista();
        vista.loadfxml("/views/documentos/viewtramite.fxml");
        vista.addLoaderContext();
        Parent view =vista.getLoader().load();
        //pasamos la información a la vista
        ViewTramite viewTramite=vista.getLoader().getController();
        viewTramite.setInfo(integrante);

        panelDetailsTramite.getChildren().add(view);
    }


}
