package com.java.desktopApp.controllers.home;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.controllers.dashboard.DashboardController;
import com.java.desktopApp.models.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HomeController implements Initializable {



    @FXML
    private Label username;

    @FXML
    private Button btnInicio;//,btnDatos,btnDocumentos;

    @FXML
    private StackPane panelshow;
    @FXML
    private VBox pnItems = null;

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        //resetar los estilos de los botones
        /*for (Node node: sidevarButtons.getChildren()){
            if(node instanceof Button){
               setStyleButton( ((Button) node),false);
            }
        }*/
        if (actionEvent.getSource() == btnInicio) {
            //setStyleButton(btnInicio,true);
            //CAMBIAR AL INICIO
            changuePanel("/views/dashboard/inicio.fxml");
        }
    }

    private void setStyleButton(Button btn,boolean type){
        if(type){
            btn.getStyleClass().add("sidevar-buuton-selected");
        }else{
            btn.getStyleClass().remove("sidevar-buuton-selected");
        }
    }

    private void changuePanel(String pathfxml) throws IOException {
        ViewManager vista= ViewManager.loadVista();
        vista.loadfxml(pathfxml);
        vista.addLoaderContext();
        Parent viewinicio=vista.getLoader().load();

        DashboardController dash=vista.getLoader().getController();
        dash.setPanel(panelshow);

        panelshow.getChildren().setAll(viewinicio);

    }

    private void setUserName(){
        String name= Auth.getInstance().getName();
        username.setText(name);
    }

    private void setViewInicio() throws IOException {
        //seleccionar el btn
        //setStyleButton(btnInicio,true);
        changuePanel("/views/dashboard/inicio.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUserName();
            setViewInicio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
