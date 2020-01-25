package com.example.androidproficiencyexcercise.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidproficiencyexcercise.R;
import com.example.androidproficiencyexcercise.backend.APIClient;
import com.example.androidproficiencyexcercise.backend.APIInterface;
import com.example.androidproficiencyexcercise.model.AboutCanada;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class SwipeRefreshLayoutFragment extends Fragment {
    private final String TAG = "SwipeRefreshLayoutFrag";

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private APIInterface apiService;

    // Required empty public constructor
    public SwipeRefreshLayoutFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        listView = view.findViewById(R.id.listView);

        getFactsAndRender();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            getFactsAndRender();
        });

        return view;
    }

    private void getFactsAndRender() {
        apiService = APIClient.getClient().create(APIInterface.class);
        Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
        nationalFactsCall.enqueue(new Callback<AboutCanada>() {
            @Override
            public void onResponse(@EverythingIsNonNull Call<AboutCanada> call, @EverythingIsNonNull Response<AboutCanada> response) {
                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                AboutCanada aboutCanada = response.body();
                setAppTitle(aboutCanada.getTitle());
                NationalFactsAdapter adapter = new NationalFactsAdapter(getContext(), R.layout.row_layout, aboutCanada.getNationalFacts());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@EverythingIsNonNull Call<AboutCanada> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void setAppTitle(String title) {
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null && title != null) {
            actionBar.setTitle(title);
        }
    }

}
