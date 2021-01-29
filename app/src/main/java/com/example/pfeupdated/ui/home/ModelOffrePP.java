package com.example.pfeupdated.ui.home;

import android.net.Uri;

public class ModelOffrePP {

    //String url;
    String title;
    Uri url;

    public ModelOffrePP() {}


    public ModelOffrePP(Uri url, String title) {
        this.url = url;
        this.title = title;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
