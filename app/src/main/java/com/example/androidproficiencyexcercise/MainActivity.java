package com.example.androidproficiencyexcercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistryOwner;

import android.os.Bundle;
import android.util.Log;

import com.example.androidproficiencyexcercise.view.SwipeRefreshLayoutFragment;
import com.example.androidproficiencyexcercise.viewmodel.SavedStateViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Avoid getting Fragment created twice
        if(savedInstanceState == null ) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SwipeRefreshLayoutFragment())
                    .commit();
        }
    }
}
