package com.example.androidproficiencyexercise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproficiencyexercise.view.SwipeRefreshLayoutFragment;

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
