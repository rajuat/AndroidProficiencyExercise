package com.example.androidproficiencyexcercise.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidproficiencyexcercise.R;
import com.example.androidproficiencyexcercise.backend.APIClient;
import com.example.androidproficiencyexcercise.backend.APIInterface;
import com.example.androidproficiencyexcercise.model.AboutCanada;
import com.example.androidproficiencyexcercise.utils.NetworkCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class SwipeRefreshLayoutFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private APIInterface apiService;
    private static final String ABOUT_CANADA_KEY = "aboutCanadaKey";
    private AboutCanada aboutCanada;

    // Required empty public constructor
    public SwipeRefreshLayoutFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ABOUT_CANADA_KEY, aboutCanada);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        listView = view.findViewById(R.id.listView);

        getFactsAndRender(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(() -> getFactsAndRender(savedInstanceState));
        return view;
    }

    private void getFactsAndRender(Bundle savedInstanceState) {
        if (NetworkCheck.isAvailable(getContext())) {
            if(savedInstanceState != null && savedInstanceState.get(ABOUT_CANADA_KEY) != null) {
                Log.d("Raju", "getFactsAndRender1: ");
                aboutCanada = (AboutCanada) savedInstanceState.get(ABOUT_CANADA_KEY);
                stopRefreshing();
                setAppTitle(aboutCanada.getTitle());
                setAdapter(aboutCanada);
            } else {
                Log.d("Raju", "getFactsAndRender2: ");
                apiService = APIClient.getClient().create(APIInterface.class);
                Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
                nationalFactsCall.enqueue(new Callback<AboutCanada>() {
                    @Override
                    public void onResponse(@EverythingIsNonNull Call<AboutCanada> call, @EverythingIsNonNull Response<AboutCanada> response) {
                        aboutCanada = response.body();
                        stopRefreshing();
                        setAppTitle(aboutCanada.getTitle());
                        setAdapter(aboutCanada);
                    }

                    @Override
                    public void onFailure(@EverythingIsNonNull Call<AboutCanada> call, Throwable t) {
                        stopRefreshing();
                        Toast.makeText(getContext(), getString(R.string.server_error), Toast.LENGTH_LONG);
                    }
                });
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.no_network), Toast.LENGTH_LONG);
        }
    }

    private void setAppTitle(String title) {
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null && title != null) {
            actionBar.setTitle(title);
        }
    }

    private void setAdapter(AboutCanada aboutCanada) {
        NationalFactsAdapter adapter = new NationalFactsAdapter(getContext(), R.layout.row_layout, aboutCanada.getNationalFacts());
        listView.setAdapter(adapter);
    }

    private void stopRefreshing() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
