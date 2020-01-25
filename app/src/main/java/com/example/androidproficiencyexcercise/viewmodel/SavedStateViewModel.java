package com.example.androidproficiencyexcercise.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.androidproficiencyexcercise.model.AboutCanada;

public class SavedStateViewModel extends ViewModel {
    private final String KEY = "KEY";
    private SavedStateHandle state;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        state = savedStateHandle;
    }

    public void setFactsAboutCanada(AboutCanada aboutCanada){
        state.set(KEY, aboutCanada);
    }

    public AboutCanada getFactsAboutCanada(){
        return state.get(KEY);
    }

}
