package com.java.desktopApp.services;

import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.services.DTO.FileTDO;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;


@Service
public class FtpService {

    @Value("${spring.ftpserver.value}")
    private String server;
    private final int port = 21;

    @Value("${spring.ftpuser.value}")
    private String user ;
    @Value("${spring.ftpkey.value}")
    private String pass ;

    public String getCredencials(){
        return server;
    }

    @Autowired
    private FileService fileService;

    public String testConected() throws IOException {
        FTPClient ftpClient= new FTPClient();
        try{
            ftpClient.connect(server,port);
            return ftpClient.login(user,pass)?"conected":"no se pudo conectar";
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    private FTPClient conect() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(server, port);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode(); // Establecer el modo pasivo
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        return  ftpClient;
    }
    public FileTDO uploadFile(File file,FileTDO document) throws IOException {
        FTPClient ftpClient=conect();
        String remothepathdel=getDirectory(document.getPath())+getFileName(document.getPath());
        String remotepath=getDirectory(document.getPath())+"R_"+getFileName(document.getPath());
        FileInputStream inputFile=new FileInputStream(file);

        boolean done=ftpClient.storeFile(remotepath,inputFile);
        if(!done){
            throw new AppException("No se pudo subirl el archivo ");
        }
        //actualiza el nuevo
        FileTDO newFile = fileService.updatePathFile(document, "/storage" + remotepath);

        //eliminamos el antiguo despues de guardar
        deleteFile(remothepathdel,ftpClient);

        return newFile;

    }

    private boolean deleteFile(String path,FTPClient ftpClient) throws IOException {
        String remotePath=getDirectory(path)+getFileName(path);
        boolean delete=ftpClient.deleteFile(remotePath);
        if (!delete){
            throw new AppException("No se pudo eliminar el archivo "+path);
        }
        return delete;
    }

    public boolean deleteArchivo(String path) throws IOException {
        FTPClient ftpClient=conect();
        boolean delete=ftpClient.deleteFile(path);
        if(!delete){
            throw new AppException("No se pudo eliminar el archivo "+path);
        }
        return delete;
    }

    public void listDirectories() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(server, port);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode();

        ftpClient.changeWorkingDirectory("requisitos/1931");
        FTPFile[] directories = ftpClient.listDirectories();

        System.out.println("Directorios " +  ":");
        for (FTPFile dir : directories) {
            System.out.println(dir.getName());
        }
    }


    private String getDirectory(String filepath) {
        int lastindex=filepath.lastIndexOf('/');
        if (lastindex > 0) {
            return filepath.substring(0, lastindex + 1).replaceFirst("/storage", "");
        }
        return  filepath;
    }

    private String getFileName(String filepath){
        int lastindex=filepath.lastIndexOf('/');
        if (lastindex >= 0 && lastindex < filepath.length() - 1) {
            return filepath.substring(lastindex + 1);
        }
        return  filepath;
    }



    public FTPFile search_path(String path) throws IOException {
        String remotePath=getDirectory(path)+getFileName(path);
        FTPClient ftpClient=conect();
        FTPFile[] files = ftpClient.listFiles(remotePath);

        if(files.length >0){
            return files[0];
        }else{
            return null;
        }
    }
}
