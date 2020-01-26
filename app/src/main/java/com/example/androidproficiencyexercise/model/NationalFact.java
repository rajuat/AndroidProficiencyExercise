package com.example.androidproficiencyexercise.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NationalFact implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    //No args constructor for use in serialization
    public NationalFact() {
    }

    /**
     * NationalFact constructor with parameters
     *
     * @param title       fact title
     * @param description fact description
     * @param imageHref   fact image url as string
     */
    public NationalFact(String title, String description, String imageHref) {
        super();
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

}
