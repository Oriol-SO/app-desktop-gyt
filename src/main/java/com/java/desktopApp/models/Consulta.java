package com.java.desktopApp.models;

public class Consulta {
    private int id;
    private String nombre;

    public Consulta(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public String ConstruirConsulta(String tabla){
        return "select*from "+tabla;
    }
    public String ConstruirConsulta(String tabla,String condicion){
        return "select*from "+tabla+" "+condicion;
    }
    public String ConstruirConsulta(String tabla,String[] columns){
        String columnasstr= String.join(",",columns);
        return "select "+columnasstr+" from "+tabla;
    }

    public String ConstruirConsulta(String tabla,String[] columns,String[] Condicion){
        String columnasstr= String.join(",",columns);
        String wheres= String.join("and ",Condicion);
        return "select "+columnasstr+" from "+tabla+" "+wheres;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
