package com.example.pfeupdated.javaClasses;

public class Societe {

    private String adress,codeSte,nomSte;

    public Societe(){}

    public Societe(String codeSte, String nomSte) {
        this.codeSte = codeSte;
        this.nomSte = nomSte;
    }

    public Societe(String adress, String codeSte, String nomSte) {
        this.adress = adress;
        this.codeSte = codeSte;
        this.nomSte = nomSte;
    }

    public String getAdress() {
        return adress;
    }

    public String getCodeSte() {
        return codeSte;
    }

    public String getNomSte() {
        return nomSte;
    }
}
