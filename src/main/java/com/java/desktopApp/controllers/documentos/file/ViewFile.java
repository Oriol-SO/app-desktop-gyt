package com.java.desktopApp.controllers.documentos.file;

import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.services.DTO.AprobadoDTO;
import com.java.desktopApp.services.DTO.FileTDO;
import com.java.desktopApp.services.DTO.ObservadoDTO;
import com.java.desktopApp.services.FileService;
import com.java.desktopApp.services.FtpService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class ViewFile implements Initializable {

    @Autowired
    private FtpService ftpService;
    @Autowired
    private FileService fileService;

    @FXML
    private Label labelPath,labelFechaFile,labelFechaAprobado,labelFechaObservado,labeltextObs,labelEstadoFile;

    @FXML
    private TextField dateFechaFile, dateFechaAprobado;

    @FXML
    private Button btnTextRoute,btnEnviar;

    private File selectedFile;


    private FileTDO file;

    private ObservadoDTO observado;

    private AprobadoDTO aprobadoDTO;

    public void setInfo(FileTDO doc){
        this.file=doc;
        //obtener añadir al file el observado y aprobado
        if(!file.isCall_aprobado()){
            file=fileService.addDateApro(file);
        }
        if (!file.isCall_observado()) {
            file=fileService.addDateObs(file);
        }
        renderInfo();
    }

    private void renderInfo(){
        labelPath.setText("Ruta: "+file.getPath());
        labelFechaFile.setText("Fecha: "+file.getFecha().toString());
        dateFechaFile.setText(file.getFecha().toString());
        if(file.getEstado()){
            labelEstadoFile.setText("Estado: Activo");
            labelEstadoFile.setStyle("-fx-text-fill:#35bf2e");
        }else{
            labelEstadoFile.setText("Estado: Eliminado");
            labelEstadoFile.setStyle("-fx-text-fill:red");
        }

        if(file.getAprobado()!=null){
            labelFechaAprobado.setText("Aprobado: "+file.getAprobado().getCreated_at().toString());
            dateFechaAprobado.setText(file.getAprobado().getCreated_at().toString());
        }else{
            labelFechaAprobado.setText("--sin aprobar--");
        }
        if(file.getObservado()!=null){
            labelFechaObservado.setText("Observado: "+file.getObservado().getCreated_at().toString());
            labeltextObs.setText("obs: "+file.getObservado().getTexto());
        }else{
            labelFechaObservado.setText("");
            labeltextObs.setText("");
        }


    }


    @FXML
    public void btnCambiarPath(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");
        selectedFile=fileChooser.showOpenDialog(new Stage());
        if(selectedFile !=null){
            btnTextRoute.setText(selectedFile.getAbsolutePath());
            btnEnviar.setDisable(false);
        }
    }

    @FXML
    public void btnEnviarArchivo(ActionEvent event) throws IOException {

        btnEnviar.setText("Enviando...");
        btnEnviar.setDisable(true);
        Task<Void> taskUpload = new Task<Void>() {
            @Override
            protected Void call(){
                try{
                    file = ftpService.uploadFile(selectedFile, file);
                }catch (Exception e){
                    throw new AppException(e.getMessage());
                }
                return null;
            }
            @Override
            protected void succeeded() {
                renderInfo();

                selectedFile=null;
                btnTextRoute.setText("Select archivo");
                btnEnviar.setDisable(true);
                btnEnviar.setText("Enviar archivo");

                Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("carga exitos");
                alert.setContentText("Se subio el archivo correctamente");
                alert.show();

            }

            @Override
            protected void failed() {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error service upload file");
                Throwable exception =getException();
                alert.setContentText(exception.getMessage());
                alert.show();
                btnEnviar.setDisable(false);
                btnEnviar.setText("Enviar archivo");
            }
        };
        new Thread(taskUpload).start();

    }

    @FXML
    public void btnFechaFile(ActionEvent event) throws IOException {
        //obtener la fecha file
        try {
            if (dateFechaFile.getText() != null) {
                String fecha = dateFechaFile.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime localDateTime = LocalDateTime.parse(fecha, formatter);
                Timestamp date = Timestamp.valueOf(localDateTime);
            }
        }catch(Exception e){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error formated fecha file");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    public void btnFechaRev(ActionEvent event){
        //obtener fecha de aprobacion
        try {
            if (dateFechaAprobado.getText() != null) {
                String fecha = dateFechaAprobado.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime localDateTime = LocalDateTime.parse(fecha, formatter);
                Timestamp date = Timestamp.valueOf(localDateTime);
            }
        }catch (Exception e){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error formated fecha aprobación");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //renderInfo();
    }
}
