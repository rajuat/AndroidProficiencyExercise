package com.example.androidproficiencyexcercise.backend;

import com.example.androidproficiencyexcercise.model.AboutCanada;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    //Retrofit interface for calling API methods
    @GET(APIClient.AppUrls.API_DETAILS_URL)
    Call<AboutCanada> getNationalFacts();
}
