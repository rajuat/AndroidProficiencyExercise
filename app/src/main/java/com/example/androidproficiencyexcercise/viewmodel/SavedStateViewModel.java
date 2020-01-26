package com.example.androidproficiencyexcercise.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.androidproficiencyexcercise.model.AboutCanada;

public class SavedStateViewModel extends ViewModel {
    private AboutCanada aboutCanada;

    // No argument constructor needed for ViewModel
    public SavedStateViewModel(){

    }

    public void setFactsAboutCanada(AboutCanada aboutCanada){
        this.aboutCanada = aboutCanada;
    }

    public AboutCanada getFactsAboutCanada(){
        return aboutCanada;
    }

}
