package com.example.androidproficiencyexercise.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkCheckTest {

    @Mock
    Context mockContext;
    @Mock
    ConnectivityManager mockConnectivityManager;
    @Mock
    NetworkInfo mockNetworkInfo;

    @Before
    public void setUp() {
        when(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);

    }

    @Test
    public void checksThatNetworkIsNotAvailable() {
        when(mockNetworkInfo.isAvailable()).thenReturn(Boolean.FALSE);
        when(mockNetworkInfo.isConnected()).thenReturn(Boolean.FALSE);
        assertFalse(NetworkCheck.isAvailable(mockContext));
    }

    @Test
    public void checksThatNetworkIsAvailable() {
        when(mockNetworkInfo.isAvailable()).thenReturn(Boolean.TRUE);
        when(mockNetworkInfo.isConnected()).thenReturn(Boolean.TRUE);
        assertTrue(NetworkCheck.isAvailable(mockContext));
    }
}
