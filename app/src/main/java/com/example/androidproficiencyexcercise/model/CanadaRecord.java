package com.example.androidproficiencyexcercise.model;

import androidx.annotation.NonNull;

public class CanadaRecord {
    private String title;
    private String description;
    private String imageReference;

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

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }


    @Override
    public String toString() {
        return title + description + imageReference;
    }
}
