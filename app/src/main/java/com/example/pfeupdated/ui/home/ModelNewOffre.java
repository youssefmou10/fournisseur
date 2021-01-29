package com.example.pfeupdated.ui.home;

import android.net.Uri;

public class ModelNewOffre {

    String title;
    Uri url;

    public ModelNewOffre(){}

    public ModelNewOffre(String title, Uri url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ModelNewOffre{" +
                "title='" + title + '\'' +
                ", url=" + url +
                '}';
    }
}
