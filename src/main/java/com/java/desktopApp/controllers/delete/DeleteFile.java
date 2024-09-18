package com.java.desktopApp.controllers.delete;

import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.services.FtpService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeleteFile {

    @Autowired
    private FtpService ftpService;

    @FXML
    private TextField txtPath;

    @FXML
    private Label labelPath,labelSize,labelDate,labelError;

    @FXML
    private Button idDelete;

    private FTPFile file;
    @FXML
    public void btnSearchPath(ActionEvent event) throws IOException {
        file=ftpService.search_path(txtPath.getText());
        labelError.setText("");
        labelDate.setText("");
        labelPath.setText("");
        labelSize.setText("");
        if(file!=null){
            //eliminar file
            labelPath.setText("PATH: "+file.getName());
            labelSize.setText("SIZE: "+file.getSize()/1024.0);
            labelDate.setText("MODIFIED: "+file.getTimestamp().getTime().toString());
            idDelete.setDisable(false);
        }else{
            labelError.setText("No se encontró el archivo");
            idDelete.setDisable(true);
        }
    }

    @FXML
    public void btnEliminar(ActionEvent event) throws IOException {

        Task<Void> taskDelete = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try{
                    boolean delete=ftpService.deleteArchivo(file.getName().toString());
                }catch (Exception e){
                    throw new AppException(e.getMessage());
                }
                return null;
            }
            @Override
            protected void succeeded() {
                labelError.setText("");
                labelDate.setText("MODIFIED");
                labelPath.setText("PATH");
                labelSize.setText("SIZE");
                idDelete.setDisable(true);
                renderAlert(Alert.AlertType.CONFIRMATION,"Eliminado con exito","Se eliminó el documento con exito");
            }
            @Override
            protected void failed() {
                Throwable exception =getException();
                renderAlert(Alert.AlertType.ERROR,"Error delete file",exception.getMessage());
            }
        };
        new Thread(taskDelete).start();
    }

    private  void renderAlert(Alert.AlertType type, String title, String message){
        Alert alert =new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
