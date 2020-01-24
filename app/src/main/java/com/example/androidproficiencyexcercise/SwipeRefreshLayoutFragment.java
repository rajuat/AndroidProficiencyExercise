package com.example.androidproficiencyexcercise;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidproficiencyexcercise.datasource.Stub;
import com.example.androidproficiencyexcercise.model.CanadaRecord;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeRefreshLayoutFragment extends Fragment {
    private final String TAG = "SwipeRefreshLayoutFrag";

    private List<CanadaRecord> recordsOfCanada;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    public SwipeRefreshLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        listView = view.findViewById(R.id.listView);

        recordsOfCanada = Stub.getRecords();
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, recordsOfCanada);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: ");
            }
        });

        return view;
    }

}
