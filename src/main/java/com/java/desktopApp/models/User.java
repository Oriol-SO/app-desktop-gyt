package com.java.desktopApp.models;

public class User {
    private String name;
    private String pass;
    private boolean auth;

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
