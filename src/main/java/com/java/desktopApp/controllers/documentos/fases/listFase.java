package com.java.desktopApp.controllers.documentos.fases;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.TramiteDTO;
import com.java.desktopApp.services.IntegranteService;
import com.java.desktopApp.services.TramiteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class listFase implements Initializable {

    @Autowired
    private TramiteService tramiteService;

    @Autowired
    private IntegranteService integranteService;
    @FXML
    private BorderPane boxinfofases;
    @FXML
    private VBox panel;
    @FXML
    private VBox VboxListFases;


    private List<FaseDTO> listFase;

    private IntegranteDTO integrante;
    private TramiteDTO tramite;
    public void setInfo(IntegranteDTO inte){
        integrante=inte;
        tramite=inte.getTramite();
    }

    @FXML
    public void buttonShowFases(ActionEvent event)  {
        tramite=tramiteService.get_fases(tramite);
        listFase=tramite.getListFases();
        integrante=integranteService.getTiemposFase(integrante);
        //rederizar las fases
        VboxListFases.getChildren().clear();
        for (FaseDTO fase:listFase){
            Button boton=new Button(fase.getNumero()+ ". "+fase.getNombre());
            boton.getStyleClass().add("boton-faseTramite");

            boton.setId("buttonfase"+fase.getId());
            boton.setOnAction(this::handleClick);
            boton.setWrapText(true);
            boton.setMnemonicParsing(false);
            boton.setMaxWidth(Double.MAX_VALUE);
            boton.setAlignment(Pos.CENTER_LEFT);
            boton.setMinHeight(Double.NEGATIVE_INFINITY);
            VboxListFases.getChildren().add(boton);
        }
    }


    private void setStyleButton(Button btn,boolean type){
        if(type){
            btn.getStyleClass().add("boton-faseTramite-selected");
        }else{
            btn.getStyleClass().remove("boton-faseTramite-selected");
        }
    }

    @FXML
    public void handleClick(ActionEvent event) {

        //resetar los estilos de los botones
        for (Node node: VboxListFases.getChildren()){
            if(node instanceof Button){
                setStyleButton( ((Button) node),false);
            }
        }
        //dar el estilo de seleccionado
       Button boton=(Button) event.getSource();
       setStyleButton(boton,true);

       //obtener el id de la fase
        String  numero= boton.getId().replaceAll("\\D","");
        Long  id=Long.parseLong(numero);

        //buscar en la lista de fases el id
        Optional<FaseDTO> Opfase= listFase.stream()
                .filter(obj->obj.getId().equals(id))
                .findFirst();

        try {
            if (Opfase.isPresent()) {
                FaseDTO fase = Opfase.get();
                //limpiar el box
                panel.getChildren().clear();
                ViewManager vista = ViewManager.loadVista();
                vista.loadfxml("/views/documentos/Fases/viewfase.fxml");
                //usar este obejeto de la fase
                vista.addLoaderContext();
                Parent view = vista.getLoader().load();
                ViewFase viewFase = vista.getLoader().getController();
                viewFase.setInfo(fase, integrante);
                panel.getChildren().add(view);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox.setVgrow(boxinfofases,Priority.ALWAYS);
    }
}
