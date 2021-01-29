package com.example.pfeupdated.ui.mesOffres;

public class MyOffre {

    private int qrcode1,logooffre1,qrcode2,logooffre2;
    private String per1,per2;

    public MyOffre(){}

    public MyOffre(int qrcode1, int logooffre1, int qrcode2, int logooffre2, String per1, String per2) {
        this.qrcode1 = qrcode1;
        this.logooffre1 = logooffre1;
        this.qrcode2 = qrcode2;
        this.logooffre2 = logooffre2;
        this.per1 = per1;
        this.per2 = per2;
    }

    public int getQrcode1() {
        return qrcode1;
    }

    public void setQrcode1(int qrcode1) {
        this.qrcode1 = qrcode1;
    }

    public int getLogooffre1() {
        return logooffre1;
    }

    public void setLogooffre1(int logooffre1) {
        this.logooffre1 = logooffre1;
    }

    public int getQrcode2() {
        return qrcode2;
    }

    public void setQrcode2(int qrcode2) {
        this.qrcode2 = qrcode2;
    }

    public int getLogooffre2() {
        return logooffre2;
    }

    public void setLogooffre2(int logooffre2) {
        this.logooffre2 = logooffre2;
    }

    public String getPer1() {
        return per1;
    }

    public void setPer1(String per1) {
        this.per1 = per1;
    }

    public String getPer2() {
        return per2;
    }

    public void setPer2(String per2) {
        this.per2 = per2;
    }
}
