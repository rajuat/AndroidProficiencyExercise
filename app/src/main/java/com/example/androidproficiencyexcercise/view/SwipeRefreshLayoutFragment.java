package com.example.androidproficiencyexcercise.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidproficiencyexcercise.R;
import com.example.androidproficiencyexcercise.backend.APIClient;
import com.example.androidproficiencyexcercise.backend.APIInterface;
import com.example.androidproficiencyexcercise.model.AboutCanada;
import com.example.androidproficiencyexcercise.utils.NetworkCheck;
import com.example.androidproficiencyexcercise.viewmodel.SavedStateViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class SwipeRefreshLayoutFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private APIInterface apiService;
    private AboutCanada aboutCanada;

    // Required empty public constructor
    public SwipeRefreshLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        listView = view.findViewById(R.id.listView);

        getCachedFactsAndRender();
        swipeRefreshLayout.setOnRefreshListener(() -> refreshFactsAndRender());
        return view;
    }

    private void getCachedFactsAndRender(){
        getFactsAndRender(false);
    }

    private void refreshFactsAndRender(){
        getFactsAndRender(true);
    }


    private void getFactsAndRender(boolean refresh) {
        if (NetworkCheck.isAvailable(getContext())) {
            SavedStateViewModel viewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);

            if (viewModel.getFactsAboutCanada() == null || refresh) {
                apiService = APIClient.getClient().create(APIInterface.class);
                Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
                nationalFactsCall.enqueue(new Callback<AboutCanada>() {
                    @Override
                    public void onResponse(@EverythingIsNonNull Call<AboutCanada> call, @EverythingIsNonNull Response<AboutCanada> response) {
                        aboutCanada = response.body();
                        //Cache data
                        viewModel.setFactsAboutCanada(aboutCanada);
                        stopRefreshing();
                        setAppTitle(aboutCanada.getTitle());
                        setAdapter(aboutCanada);
                    }

                    @Override
                    public void onFailure(@EverythingIsNonNull Call<AboutCanada> call, Throwable t) {
                        stopRefreshing();
                        Toast.makeText(getContext(), getString(R.string.server_error), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                // Render data from cache
                aboutCanada = viewModel.getFactsAboutCanada();
                stopRefreshing();
                setAppTitle(aboutCanada.getTitle());
                setAdapter(aboutCanada);
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.no_network), Toast.LENGTH_LONG).show();
        }
    }

    private void setAppTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
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
