package com.java.desktopApp.controllers.documentos.file;

import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.services.DTO.FileTDO;
import com.java.desktopApp.services.FileService;
import com.java.desktopApp.services.FtpService;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ViewFile{

    @Autowired
    private FtpService ftpService;
    @Autowired
    private FileService fileService;

    @FXML
    private Label labelPath,labelFechaFile,labelFechaAprobado,labelFechaObservado,labeltextObs,labelEstadoFile;
    @FXML
    private TextField dateFechaFile, dateFechaAprobado;
    @FXML
    private Button btnTextRoute,btnEnviar,buttonUpdateFechaRev;

    private File selectedFile;
    private FileTDO file;

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
            dateFechaAprobado.setDisable(true);
            buttonUpdateFechaRev.setDisable(true);
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
                renderAlert(Alert.AlertType.CONFIRMATION,"carga exitosa","Se subió correctamente el archivo");
            }

            @Override
            protected void failed() {
                Throwable exception =getException();
                renderAlert(Alert.AlertType.ERROR,"Error upload file",exception.getMessage());

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
                setUpdateDate(1,date);
            }
        }catch(Exception e){
            renderAlert(Alert.AlertType.ERROR,"Error update fecha file",e.getMessage());
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
                setUpdateDate(2,date);
            }
        }catch (Exception e){
            renderAlert(Alert.AlertType.ERROR,"Error update fecha aprobación",e.getMessage());
        }
    }

    private void setUpdateDate(int tipo,Timestamp date){
        Task<Void> taskUpdate = new Task<Void>() {
            @Override
            protected Void call() {
                try{
                    if(tipo==1){
                        file=fileService.updateFechaFile(file,date);
                    }else if(tipo==2){
                        file=fileService.updateFechaApro(file,date);
                    }
                }catch (Exception e){
                    throw new AppException(e.getMessage());
                }
                return null;
            }
            @Override
            protected void succeeded() {
                renderInfo();
                renderAlert(Alert.AlertType.CONFIRMATION,tipo==1?"Fecha file":"Fecha Aprobación","Fecha actualizada con exito a :"+date);
            }

            @Override
            protected void failed() {
                Throwable exception =getException();
                renderAlert(Alert.AlertType.ERROR,"Error update fecha",exception.getMessage());
            }
        };
        new Thread(taskUpdate).start();
    }
    private  void renderAlert(Alert.AlertType type, String title, String message){
        Alert alert =new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

}
