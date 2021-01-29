package com.example.pfeupdated.javaClasses;

public class TypeOffre
{
    private  String idType,nomType;

    public TypeOffre(String idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public TypeOffre() {
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
}
