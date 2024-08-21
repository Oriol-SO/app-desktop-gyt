package com.java.desktopApp.controllers.auth;

import com.java.desktopApp.config.ViewManager;
import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.models.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class LoginController {

    @FXML
    private Label errorLoginId;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;

    @Value("${spring.dbkey.value}")
    private String dbkey;

    @Value("${spring.dbuser.value}")
    private String dbuser;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showerror(String message){
        errorLoginId.setText(message);
    }

    @FXML
    public void presslogin(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        try {
            showerror("");
            UserService acces = new UserService();
            acces.setUservalue(txtUser,dbuser);
            acces.setPasswordvalue(txtPassword,dbkey);

            Auth user = new Auth();
            user = acces.login();
            if(user.isAuth()){
                ViewManager vista= ViewManager.loadVista();
                vista.loadfxml("/views/home/home.fxml");
                vista.addLoaderContext();
                vista.showView();
                /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home/home.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();*/
            }

        }catch (AppException error){
            showerror(error.getMessage());
        }
    }

}
