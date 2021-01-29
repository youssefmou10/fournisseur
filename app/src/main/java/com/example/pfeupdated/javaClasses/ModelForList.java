package com.example.pfeupdated.javaClasses;

import androidx.recyclerview.widget.RecyclerView;

public class ModelForList  {

    private Integer image;
    private String title;

    public ModelForList() {

    }

    public ModelForList(Integer image, String title) {

        this.image = image;
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
