package com.java.desktopApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

public class Auth {

    private static Auth instancia;
    private String name;
    private String pass;
    private boolean auth=false;

    public static Auth getInstance(){
        if(instancia==null){
            instancia=new Auth();
        }
        return instancia;
    }

    public String getName() {
        return name;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

}
