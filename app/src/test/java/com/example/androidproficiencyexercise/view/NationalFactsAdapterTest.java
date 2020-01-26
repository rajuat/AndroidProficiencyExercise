package com.example.androidproficiencyexercise.view;


import android.app.Application;

import androidx.test.core.app.ApplicationProvider;

import com.example.androidproficiencyexercise.model.NationalFact;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static android.os.Build.VERSION_CODES.O_MR1;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = O_MR1)

public class NationalFactsAdapterTest {

    private NationalFactsAdapter adapter;
    private Application context;

    @Before public void setUp() {
        NationalFact fact = new NationalFact("title", "description", "imageHref");
        List<NationalFact> list = Arrays.asList(fact, fact, fact);

        context = ApplicationProvider.getApplicationContext();
        adapter = new NationalFactsAdapter(context, 0, list);
    }

    @Test
    public void verifyContext() {
        assertEquals(context, adapter.getContext());
    }

    @Test
    public void verifyListContent() {
        assertEquals(3, adapter.getCount());
        assertEquals("title", adapter.getItem(0).getTitle());
        assertEquals("description", adapter.getItem(1).getDescription());
        assertEquals("imageHref", adapter.getItem(1).getImageHref());
    }

}
