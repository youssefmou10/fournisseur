package com.example.pfeupdated.javaClasses;

import java.util.Date;

public class Fornisseur extends User
{
    private  String adress,logo;

    public Fornisseur() {
    }

    public Fornisseur(String nom, String prenom, String telephone, String motsDePasse, String adressmail, String active, Date dateNessance, String adress, String logo,String id) {
        super(nom, prenom, telephone, motsDePasse, adressmail, active, dateNessance,id);
        this.adress = adress;
        this.logo = logo;
    }

    public Fornisseur(String adress, String logo) {
        this.adress = adress;
        this.logo = logo;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
