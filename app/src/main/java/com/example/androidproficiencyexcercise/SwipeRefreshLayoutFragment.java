package com.example.androidproficiencyexcercise;


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

import com.example.androidproficiencyexcercise.backend.APIClient;
import com.example.androidproficiencyexcercise.backend.APIInterface;
import com.example.androidproficiencyexcercise.model.AboutCanada;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeRefreshLayoutFragment extends Fragment {
    private final String TAG = "SwipeRefreshLayoutFrag";

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private APIInterface apiService;

    // Required empty public constructor
    public SwipeRefreshLayoutFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        listView = view.findViewById(R.id.listView);

        apiService = APIClient.getClient().create(APIInterface.class);
        Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
        nationalFactsCall.enqueue(new Callback<AboutCanada>() {
            @Override
            public void onResponse(@EverythingIsNonNull Call<AboutCanada> call, @EverythingIsNonNull Response<AboutCanada> response) {
                AboutCanada aboutCanada = response.body();

                AppCompatActivity activity = ((AppCompatActivity) getActivity());
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null && aboutCanada.getTitle() != null) {
                    actionBar.setTitle(aboutCanada.getTitle());
                }
                NationalFactsAdapter adapter = new NationalFactsAdapter(getContext(), R.layout.row_layout, aboutCanada.getNationalFacts());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@EverythingIsNonNull Call<AboutCanada> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> Log.d(TAG, "onRefresh: "));

        return view;
    }

}
