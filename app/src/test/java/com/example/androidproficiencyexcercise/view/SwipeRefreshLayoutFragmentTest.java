package com.example.androidproficiencyexcercise.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidproficiencyexcercise.MainActivity;
import com.example.androidproficiencyexcercise.model.AboutCanada;
import com.example.androidproficiencyexercise.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.List;

import static android.os.Build.VERSION_CODES.O_MR1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = O_MR1)
public class SwipeRefreshLayoutFragmentTest {

    SwipeRefreshLayoutFragment fragment;
    AppCompatActivity activity;

    @Before
    public void setUp() {
        ActivityController<MainActivity> mainActivityController = Robolectric.buildActivity(MainActivity.class);
        activity = mainActivityController.create().resume().get();
        fragment = (SwipeRefreshLayoutFragment) activity.getSupportFragmentManager().getFragments().get(0);
    }


    @Test
    public void shouldCallOnCreateView(){
        fragment.onCreateView(activity.getLayoutInflater(), null, null);
        View view = fragment.getView();
        ListView listView = view.findViewById(R.id.listView);
        assertTrue(listView.isEnabled());

        SwipeRefreshLayout layout = view.findViewById(R.id.swipeToRefresh);
        assertEquals(1, layout.indexOfChild(listView));
    }


}
