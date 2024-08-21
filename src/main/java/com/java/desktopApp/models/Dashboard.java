package com.java.desktopApp.models;

public class Dashboard {

    public static Dashboard dash;

    public static Dashboard getDash(){
        if(dash==null){
            dash=new Dashboard();
        }
        return dash;
    }

    private Long num_registros;
    private Long num_tramites;

    public Long getNum_registros() {
        return num_registros;
    }

    public Long getNum_tramites(){
        return num_tramites;
    }

    public void setNum_registros(Long num_registros) {
        this.num_registros = num_registros;
    }

    public void setNum_tramites(Long num_tramites) {
        this.num_tramites = num_tramites;
    }
}
