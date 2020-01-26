package com.example.androidproficiencyexercise.view;

import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidproficiencyexercise.MainActivity;
import com.example.androidproficiencyexercise.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.O_MR1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = O_MR1)
public class SwipeRefreshLayoutFragmentTest {

    private SwipeRefreshLayoutFragment fragment;
    private AppCompatActivity activity;

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
