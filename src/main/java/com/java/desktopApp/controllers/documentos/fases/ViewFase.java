package com.java.desktopApp.controllers.documentos.fases;

import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.FaseRequisitoTDO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.TiempoDTO;
import com.java.desktopApp.services.FaseRequisitoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ViewFase {

    @Autowired
    private FaseRequisitoService faseRequisitoService;


    @FXML
    private Label labelNombre,labelFechaInicio,labelFechaFin;
    @FXML
    private VBox boxListRequisitos;

    private TiempoDTO tiempo;
    private FaseDTO fase;
    private List<FaseRequisitoTDO> listRequisito;


    private TiempoDTO searchTiempo(List<TiempoDTO> listaTiempos,int numero){
        Optional<TiempoDTO> OpTiempo= listaTiempos.stream()
                .filter(obj->obj.getNumero_fase()==numero)
                .findFirst();
        if(OpTiempo.isPresent()){
            return OpTiempo.get();
        }
        return null;
    }


    public void setInfo(FaseDTO fase_, IntegranteDTO integrante){
        this.fase=fase_;
        labelNombre.setText(fase.getNumero()+". "+fase.getNombre());
        tiempo=searchTiempo(integrante.getListTiempos(),fase.getNumero());
        labelFechaInicio.setText(tiempo.getFecha_inicio().toString());
        labelFechaFin.setText(tiempo.getFecha_fin().toString());
    }


    @FXML
    public void buttonShowRequisitos(ActionEvent event){
        boxListRequisitos.getChildren().clear();
        //obtenemos la lista de requisitos de la fase
        //System.out.println(faseRequisitoService.getRequisitosFase__(this.fase.getId()));

        listRequisito=faseRequisitoService.getRequisitosFase__(this.fase.getId());
        int i=1;
        for (FaseRequisitoTDO req:listRequisito){
            Button boton=new Button((i++)+ ". "+req.getNombre());
            boton.getStyleClass().add("boton-faseTramite");
            boton.setId("buttonfase"+req.getId());
            //boton.setOnAction(this::handleClick);
            boton.setWrapText(true);
            boton.setMnemonicParsing(false);
            boton.setMaxWidth(Double.MAX_VALUE);
            boton.setAlignment(Pos.CENTER_LEFT);
            boton.setMinHeight(Double.NEGATIVE_INFINITY);
            boxListRequisitos.getChildren().add(boton);
        }
    }
}
