package com.example.androidproficiencyexcercise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproficiencyexcercise.view.SwipeRefreshLayoutFragment;
import com.example.androidproficiencyexercise.R;

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
