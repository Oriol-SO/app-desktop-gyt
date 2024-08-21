package com.java.desktopApp.controllers.auth;

import com.java.desktopApp.exceptions.AppException;
import com.java.desktopApp.models.Auth;
import javafx.scene.control.TextField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private String dbkey;
    private String dbuser;

    private String uservalue;
    private String passwordvalue;

    public void setUservalue(TextField uservalue,String dbuser) {
        this.uservalue = uservalue.getText();
        this.dbuser=dbuser;

    }

    public void setPasswordvalue(TextField passwordvalue,String dbkey) {
        this.passwordvalue = passwordvalue.getText();
        this.dbkey=dbkey;
    }

    private void verificationresult(){
        if(uservalue.isBlank() || uservalue.isEmpty()){
            throw new AppException("El campo usuario es requerido");
        }
        if(passwordvalue.isBlank() || passwordvalue.isEmpty()){
            throw new AppException("el campo password es requerido");
        }
    }

    private Auth tryaccess() throws NoSuchAlgorithmException {
        if(!dbuser.equals(uservalue)){
            throw new AppException("El usuario es incorrecto");
        }
        //encripted
        String hash=encripted(passwordvalue);
        //verificar
        if(!dbkey.equals(hash)){
            throw new AppException("La contraseña es incorrecta");
        }
        Auth.getInstance().setName(uservalue);
        Auth.getInstance().setAuth(true);
        return Auth.getInstance();
    }

    private String encripted(String password) throws NoSuchAlgorithmException {
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public Auth login() throws NoSuchAlgorithmException {
        verificationresult();
        return tryaccess();
    }

}
