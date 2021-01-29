package com.example.pfeupdated.javaClasses;

import android.net.Uri;

import java.util.Date;

public class Salariee extends User
{
    private String idSte;
    private Uri profileImg;

    public String getIdSte() {
        return idSte;
    }

    public void setIdSte(String idSte) {
        this.idSte = idSte;
    }

    public Uri getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Uri profileImg) {
        this.profileImg = profileImg;
    }

    public Salariee() {
    }

    public Salariee(String nom, String prenom, String telephone, String motsDePasse, String adressmail, String active, Date dateNessance, String idSte,String id,Uri profileImg) {
        super(nom, prenom, telephone, motsDePasse, adressmail, active, dateNessance,id);
        this.idSte = idSte;
        this.profileImg = profileImg;
    }

    public Salariee(String idSte) {
        this.idSte = idSte;
    }

}
