package com.example.movies.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.databinding.ActivityHomeBinding;
import com.example.movies.interfaces.MovieCallback;
import com.example.movies.network.model.GetPopularMoviesResponse;
import com.example.movies.view.adapters.MoviesAdapter;
import com.example.movies.view_model.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MovieCallback {

    private ActivityHomeBinding homeBinding;
    private GetPopularMoviesResponse response;
    private List<GetPopularMoviesResponse.Result> movies;
    private MoviesAdapter moviesAdapter;
    private int page = 1;
    private MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(HomeActivity.this,
                R.layout.activity_home);

        initListeners();
        getIntentExtras();

    }

    private void initListeners() {

        moviesViewModel = new ViewModelProvider(HomeActivity.this)
                .get(MoviesViewModel.class);
        moviesViewModel.returnPopularMovies().observe(HomeActivity.this, paggingResponse -> {

            homeBinding.lottie.setVisibility(View.GONE);

            if (paggingResponse != null && paggingResponse.getResults().size() > 0) {

                movies.addAll(paggingResponse.getResults());
                moviesAdapter.notifyDataSetChanged();

            }

        });

    }

    private void getIntentExtras() {

        if (getIntent().getSerializableExtra(getString(R.string.response)) != null) {
            response = (GetPopularMoviesResponse) getIntent()
                    .getSerializableExtra(getString(R.string.response));
            setRecyclerViewAdapter(response);
        }

    }

    private void setRecyclerViewAdapter(GetPopularMoviesResponse response) {

        movies = new ArrayList<>();
        movies.addAll(response.getResults());
        moviesAdapter = new MoviesAdapter(movies, this);
        homeBinding.rvMoviesList.setAdapter(moviesAdapter);

    }

    @Override
    public void onMovieClicked(int position) {

    }

    @Override
    public void onLastMovieItemReached() {

//        Check if we didn't reach the movies end
        if (response.getResults().size() == 20) {
            homeBinding.lottie.setVisibility(View.VISIBLE);
            page++;
            moviesViewModel.getPopularMovies(page, getString(R.string.api_key));
        }

    }
}