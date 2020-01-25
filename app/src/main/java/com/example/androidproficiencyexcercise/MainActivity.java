package com.example.androidproficiencyexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidproficiencyexcercise.view.SwipeRefreshLayoutFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new SwipeRefreshLayoutFragment())
                .commit();
    }
}
