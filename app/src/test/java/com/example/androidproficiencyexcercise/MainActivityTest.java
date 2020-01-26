package com.example.androidproficiencyexcercise;

import androidx.fragment.app.Fragment;

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
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = O_MR1)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setUp() {
        ActivityController<MainActivity> mainActivityController = Robolectric.buildActivity(MainActivity.class);
        activity = mainActivityController.create().resume().get();
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

    @Test
    public void determinesTitle(){
        assertEquals("Android Proficiency Exercise", activity.getTitle().toString());
    }

    @Test
    public void hasAFragment(){
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        assertEquals(1, fragments.size());
    }

}
