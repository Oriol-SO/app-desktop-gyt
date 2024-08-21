package com.java.desktopApp.controllers.documentos;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.controllers.documentos.fases.listFase;
import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.TramiteDTO;
import com.java.desktopApp.services.TramiteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

@Component
public class ViewTramite implements Initializable {

    @Autowired
    private TramiteService tramiteService;

    @FXML
    private Label labelTipoTramite,labelEstado,labelInicio,labelEnviado,labelNumIntegrantes,labelFaseActual;

    @FXML
    private BorderPane boxBorderPane;

    private  IntegranteDTO integrante;
    private TramiteDTO tramite;

    public void setInfo(IntegranteDTO integrante) throws IOException {
        this.integrante=integrante;
        this.tramite=integrante.getTramite();

        labelTipoTramite.setText(integrante.getTramite().getTipo_tramite());
        if (integrante.isEstado()){
            labelEstado.setText("Finalizado");
            labelEstado.setStyle("-fx-text-fill:green");
        } else if (integrante.isUso()) {
            labelEstado.setText("En curso");
            labelEstado.setStyle("-fx-text-fill:blue");
        }else{
            labelEstado.setText("Retirado");
            labelEstado.setStyle("-fx-text-fill:#e10101");
        }
        int ints=integrante.getTramite().getIntegrantes();
        labelNumIntegrantes.setText(Integer.toString(ints));
        labelFaseActual.setText(Integer.toString(integrante.getFase_actual()));
        labelInicio.setText(integrante.getFec_inicio().toString());
        Timestamp envio=integrante.getTramite().getFec_envio();
        labelEnviado.setText(envio==null?"Sin enviar":envio.toString());

        renderizarBoxFases();
    }


    public void renderizarBoxFases() throws IOException {
        //mostrar las fases
        ViewManager vista = ViewManager.loadNewVista();
        vista.loadfxml("/views/documentos/Fases/listfase.fxml");
        vista.addLoaderContext();
        Parent view = vista.getLoader().load();
        listFase item = vista.getLoader().getController();
        item.setInfo(this.integrante);
        boxBorderPane.setCenter(view);
        //boxBorderPane.getChildren().add(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
