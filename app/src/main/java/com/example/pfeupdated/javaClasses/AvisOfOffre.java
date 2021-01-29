package com.example.pfeupdated.javaClasses;

public class AvisOfOffre {
    String idSal,idOffre,comment,idAvie,active;
    float rating;

    public String getIdSal() {
        return idSal;
    }

    public void setIdSal(String idSal) {
        this.idSal = idSal;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getIdAvie() { return idAvie; }

    public void setIdAvie(String idAvie) { this.idAvie = idAvie; }

    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }

    public AvisOfOffre() {}

    public AvisOfOffre(String idSal, String idOffre, String comment, float rating,String active) {
        this.idSal = idSal;
        this.idOffre = idOffre;
        this.comment = comment;
        this.rating = rating;
        this.idAvie = idAvie;
        this.active = active;
    }

    @Override
    public String toString() {
        return "AvisOfOffre{" +
                "idSal='" + idSal + '\'' +
                ", idOffre='" + idOffre + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating + '\'' +
                ", active=" + active +
                '}';
    }
}
