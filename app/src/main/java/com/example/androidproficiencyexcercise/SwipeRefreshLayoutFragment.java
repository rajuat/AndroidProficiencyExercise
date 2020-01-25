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
import com.example.androidproficiencyexcercise.datasource.Stub;
import com.example.androidproficiencyexcercise.model.AboutCanada;
import com.example.androidproficiencyexcercise.model.CanadaRecord;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeRefreshLayoutFragment extends Fragment {
    private final String TAG = "SwipeRefreshLayoutFrag";

    private List<CanadaRecord> recordsOfCanada;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    private APIInterface apiService;

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

        apiService = APIClient.getClient().create(APIInterface.class);
        Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
        nationalFactsCall.enqueue(new Callback<AboutCanada>() {
            @Override
            public void onResponse(Call<AboutCanada> call, Response<AboutCanada> response) {
                AboutCanada body = response.body();

                AppCompatActivity activity = ((AppCompatActivity) getActivity());
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null && body.getTitle() != null) {
                    actionBar.setTitle(body.getTitle());
                }
                NationalFactsAdapter adapter = new NationalFactsAdapter(getContext(), activity, R.layout.row_layout, body.getNationalFacts());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AboutCanada> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        recordsOfCanada = Stub.getRecords();



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: ");
            }
        });

        return view;
    }

}
