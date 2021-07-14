package com.example.movies.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movies.R;
import com.example.movies.databinding.ActivityHomeBinding;
import com.example.movies.interfaces.MovieCallback;
import com.example.movies.network.model.GetPopularMoviesResponse;
import com.example.movies.view.adapters.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MovieCallback {

    private ActivityHomeBinding homeBinding;
    private GetPopularMoviesResponse response;
    private List<GetPopularMoviesResponse.Result> movies;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(HomeActivity.this,
                R.layout.activity_home);

        getIntentExtras();

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

    }
}