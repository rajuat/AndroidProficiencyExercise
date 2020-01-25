package com.example.androidproficiencyexcercise.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutCanada {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    private List<NationalFact> nationalFacts = null;

    //No args constructor for use in serialization
    public AboutCanada() {
    }

    /**
     * AboutCanada response constructor with parameters
     *
     * @param title title of feed list
     * @param nationalFacts feeds list
     */
    public AboutCanada(String title, List<NationalFact> nationalFacts) {
        super();
        this.title = title;
        this.nationalFacts = nationalFacts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NationalFact> getNationalFacts() {
        return nationalFacts;
    }

    public void setNationalFacts(List<NationalFact> nationalFacts) {
        this.nationalFacts = nationalFacts;
    }
}
