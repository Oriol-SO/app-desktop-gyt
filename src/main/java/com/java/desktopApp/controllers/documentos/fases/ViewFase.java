package com.java.desktopApp.controllers.documentos.fases;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.services.DTO.FaseDTO;
import com.java.desktopApp.services.DTO.FaseRequisitoTDO;
import com.java.desktopApp.services.DTO.IntegranteDTO;
import com.java.desktopApp.services.DTO.TiempoDTO;
import com.java.desktopApp.services.FaseRequisitoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class ViewFase implements Initializable {

    @Autowired
    private FaseRequisitoService faseRequisitoService;


    @FXML
    private Label labelNombre,labelFechaInicio,labelFechaFin;
    @FXML
    private VBox boxListRequisitos;
    @FXML
    private BorderPane infoViewFase;

    private TiempoDTO tiempo;
    private FaseDTO fase;
    private List<FaseRequisitoTDO> listRequisito;

    private IntegranteDTO inte;


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
        this.inte=integrante;

        labelNombre.setText(fase.getNumero()+". "+fase.getNombre());
        tiempo=searchTiempo(integrante.getListTiempos(),fase.getNumero());
        if(tiempo!=null){
            labelFechaInicio.setText(tiempo.getFecha_inicio().toString());
            labelFechaFin.setText(tiempo.getFecha_fin()!=null?tiempo.getFecha_fin().toString():"null");
        }else{
            labelFechaInicio.setText("null");
            labelFechaFin.setText("null");
        }

    }


    @FXML
    public void buttonShowRequisitos(ActionEvent event) throws IOException {
        boxListRequisitos.getChildren().clear();
        listRequisito=faseRequisitoService.getRequisitosFase__(this.fase.getId());
        int i=1;
        for (FaseRequisitoTDO req:listRequisito){

            ViewManager vista=ViewManager.loadNewVista();
            vista.loadfxml("/views/documentos/Fases/viewrequisito.fxml");
            //vista.addLoaderContext();
            Parent view=vista.getLoader().load();

            ViewRequisito viewRequisito=vista.getLoader().getController();
            viewRequisito.setInfo(req,this.inte,i++);
            boxListRequisitos.getChildren().add(view);
           /* Button boton=new Button((i++)+ ". "+req.getNombre());
            boton.getStyleClass().add("boton-faseTramite");
            boton.setId("buttonfase"+req.getId());
            boton.setWrapText(true);
            boton.setMnemonicParsing(false);
            boton.setMaxWidth(Double.MAX_VALUE);
            boton.setAlignment(Pos.CENTER_LEFT);
            boton.setMinHeight(Double.NEGATIVE_INFINITY);*/

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox.setVgrow(infoViewFase, Priority.ALWAYS);
    }
}
