package com.java.desktopApp.models;

import com.java.desktopApp.entities.Persona;
import lombok.Data;

import java.util.List;

@Data
public class PersonaModel extends Persona {
    private List<IntegranteModel> tramiteIntegrantes;

    public PersonaModel(Persona person){
        this.setId(person.getId());
        this.setEmail(person.getEmail());
        this.setNom(person.getNom());
        this.setApePat(person.getApePat());
        this.setApeMat(person.getApeMat());
        this.setNumDoc(person.getNumDoc());
        this.setNumcel(person.getNumcel());
        this.setCod_alum(person.getCod_alum());
        this.setFac_nom(person.getFac_nom());
        this.setEsc_nom(person.getEsc_nom());
        this.setEspecialidad(person.getEspecialidad());

    }
}
