package com.java.desktopApp.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class ViewManager {

    private static  ViewManager vista;

    public static ViewManager loadVista(){
        if(vista==null){
            vista= new ViewManager();
        }
        return vista;
    }

    public static ViewManager loadNewVista(){
        vista= new ViewManager();
        return vista;
    }

    private Stage stage;
    private FXMLLoader  loader;
    private ConfigurableApplicationContext context;

    public void loadStage(Stage stages){
        stage=stages;
    }
    /*public  void loadContext(ConfigurableApplicationContext contexto){
        context=contexto;
    }*/

    /*public ConfigurableApplicationContext getContext(){
        return context;
    }*/

    public Stage getStage(){
        return stage;
    }

    public  void loadfxml(String fxmlPath){
        loader = new FXMLLoader(ViewManager.class.getResource(fxmlPath));
    }

    public void addLoaderContext(){
        ContextManager contexto=ContextManager.getContexto();
        loader.setControllerFactory(contexto.getContext()::getBean);
    }

    public FXMLLoader getLoader(){
        return loader;
    }

    public void showView() throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
