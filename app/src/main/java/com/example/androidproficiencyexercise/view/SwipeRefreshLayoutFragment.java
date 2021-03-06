package com.example.androidproficiencyexercise.view;

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

import com.example.androidproficiencyexercise.R;
import com.example.androidproficiencyexercise.backend.APIClient;
import com.example.androidproficiencyexercise.backend.APIInterface;
import com.example.androidproficiencyexercise.model.AboutCanada;
import com.example.androidproficiencyexercise.utils.NetworkCheck;
import com.example.androidproficiencyexercise.viewmodel.SavedStateViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * The SwipeRefreshLayoutFragment is used to display a ListView inside of SwipeRefreshLayout.
 * SwipeRefreshLayout is use to enable refresh of list with a swipe gesture.
 * ListView holds the list of facts about Canada.
 * The backend is call by Retrofit client and creates a handle called apiService for getting data.
 */
public class SwipeRefreshLayoutFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private APIInterface apiService;
    private AboutCanada aboutCanada;

    // Required empty public constructor
    public SwipeRefreshLayoutFragment() {
    }

    /**
     * This lifecycle hook inflates the layout and calls the getCachedFactsAndRender method.
     * If the user refreshes the app, it calls the refreshFactsAndRender by calling the apiService.
     */
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

    private void getCachedFactsAndRender() {
        getFactsAndRender(false);
    }

    private void refreshFactsAndRender() {
        getFactsAndRender(true);
    }

    private void getFactsAndRender(boolean refresh) {
        if (NetworkCheck.isAvailable(getContext())) {
            // ViewModel pattern for setting and getting cache
            SavedStateViewModel viewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);

            if (viewModel.getFactsAboutCanada() == null || refresh) {
                getFactsFromBackendAndRender(viewModel);
            } else {
                getFactsFromCacheAndRender(viewModel);
            }
        } else {
            // Inform the user when there is no network.
            Toast.makeText(getContext(), getString(R.string.no_network), Toast.LENGTH_LONG).show();
        }
    }

    private void getFactsFromBackendAndRender(SavedStateViewModel viewModel) {
        apiService = APIClient.getClient().create(APIInterface.class);
        Call<AboutCanada> nationalFactsCall = apiService.getNationalFacts();
        nationalFactsCall.enqueue(new Callback<AboutCanada>() {
            /**
             * On successful response, cache the data, stop the refresh icon if any,
             * set the app title and delegate responsibility to the adapter.
             */
            @Override
            public void onResponse(@EverythingIsNonNull Call<AboutCanada> call, @EverythingIsNonNull Response<AboutCanada> response) {
                aboutCanada = response.body();
                //Cache data
                viewModel.setFactsAboutCanada(aboutCanada);
                stopRefreshing();
                setAppTitle(aboutCanada.getTitle());
                setAdapter(aboutCanada);
            }

            /**
             * Informs the user for server errors.
             */
            @Override
            public void onFailure(@EverythingIsNonNull Call<AboutCanada> call, Throwable t) {
                stopRefreshing();
                Toast.makeText(getContext(), getString(R.string.server_error), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getFactsFromCacheAndRender(SavedStateViewModel viewModel) {
        aboutCanada = viewModel.getFactsAboutCanada();
        stopRefreshing();
        setAppTitle(aboutCanada.getTitle());
        setAdapter(aboutCanada);
    }

    private void stopRefreshing() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
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
}
