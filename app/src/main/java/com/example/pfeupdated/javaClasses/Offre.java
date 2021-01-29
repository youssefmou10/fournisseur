package com.example.pfeupdated.javaClasses;

import android.net.Uri;

import java.util.Date;

public class Offre
{
    private String nomOffre,discription,/*imageOffre,*/imageSlider,valider,address;
    private String datePub,dateDebut,dateFin;
    private String idFornisseur,idType;
    private String idOffre;
    private Uri imageOffre;
    public Offre() {
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

//    public String getImageOffre() {
//        return imageOffre;
//    }
//
//    public void setImageOffre(String imageOffre) {
//        this.imageOffre = imageOffre;
//    }

    public String getImageSlider() {
        return imageSlider;
    }

    public void setImageSlider(String imageSlider) {
        this.imageSlider = imageSlider;
    }

    public String getValider() {
        return valider;
    }

    public void setValider(String valider) {
        this.valider = valider;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getIdFornisseur() {
        return idFornisseur;
    }

    public void setIdFornisseur(String idFornisseur) {
        this.idFornisseur = idFornisseur;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public Uri getImageOffre() {
        return imageOffre;
    }

    public void setImageOffre(Uri imageOffre) {
        this.imageOffre = imageOffre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "nomOffre='" + nomOffre + '\'' +
                ", discription='" + discription + '\'' +
                ", imageOffre='" + imageOffre + '\'' +
                ", imageSlider='" + imageSlider + '\'' +
                ", valider='" + valider + '\'' +
                ", datePub=" + datePub +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", idFornisseur='" + idFornisseur + '\'' +
                ", idType='" + idType + '\'' +
                ", idOffre='" + idOffre + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

//    public Offre(String nomOffre, String discription, String imageOffre, String imageSlider, String valider, Date datePub, Date dateDebut, Date dateFin, String idFornisseur, String idType, String idOffre) {
//        this.nomOffre = nomOffre;
//        this.discription = discription;
//        this.imageOffre = imageOffre;
//        this.imageSlider = imageSlider;
//        this.valider = valider;
//        this.datePub = datePub;
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.idFornisseur = idFornisseur;
//        this.idType = idType;
//        this.idOffre = idOffre;
//    }


    public Offre(String nomOffre, String discription, Uri imageOffre, String imageSlider, String valider, String datePub, String dateDebut, String dateFin, String idFornisseur, String idType, String idOffre, String address) {
        this.nomOffre = nomOffre;
        this.discription = discription;
        this.imageSlider = imageSlider;
        this.valider = valider;
        this.datePub = datePub;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idFornisseur = idFornisseur;
        this.idType = idType;
        this.idOffre = idOffre;
        this.imageOffre = imageOffre;
        this.address = address;
    }
}
