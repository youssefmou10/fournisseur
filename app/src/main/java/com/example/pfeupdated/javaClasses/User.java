package com.example.pfeupdated.javaClasses;

import java.util.Date;

public class User
{
    private String nom,prenom,telephone,motsDePasse,adressmail,active,id;
    private Date dateNessance;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotsDePasse() {
        return motsDePasse;
    }

    public void setMotsDePasse(String motsDePasse) {
        this.motsDePasse = motsDePasse;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdressmail() {
        return adressmail;
    }

    public void setAdressmail(String adressmail) {
        this.adressmail = adressmail;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getDateNessance() {
        return dateNessance;
    }

    public String getId() {
        return id;
    }

    public void setDateNessance(Date dateNessance) {
        this.dateNessance = dateNessance;
    }

    public User(String nom, String prenom, String telephone, String motsDePasse, String adressmail, String active, Date dateNessance, String id) {
        this.nom = nom;
        this.id = id;
        this.prenom = prenom;
        this.telephone = telephone;
        this.motsDePasse = motsDePasse;
        this.adressmail = adressmail;
        this.active = active;
        this.dateNessance = dateNessance;
    }

    public User() {
    }
}
