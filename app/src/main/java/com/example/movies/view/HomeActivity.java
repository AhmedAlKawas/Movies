package com.example.movies.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.databinding.ActivityHomeBinding;
import com.example.movies.interfaces.MovieCallback;
import com.example.movies.model.Movie;
import com.example.movies.network.model.GetPopularMoviesResponse;
import com.example.movies.view.adapters.MoviesAdapter;
import com.example.movies.view_model.MoviesViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MovieCallback {

    private ActivityHomeBinding homeBinding;
    private GetPopularMoviesResponse response;
    private List<Movie> movies;
    private MoviesAdapter moviesAdapter;
    private int page = 1;
    private MoviesViewModel moviesViewModel;
    private FirebaseAnalytics analytics;
    private Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(HomeActivity.this,
                R.layout.activity_home);

        initListeners();
        getIntentExtras();

        analytics = FirebaseAnalytics.getInstance(HomeActivity.this);
        params = new Bundle();

    }

    private void initListeners() {

        moviesViewModel = new ViewModelProvider(HomeActivity.this)
                .get(MoviesViewModel.class);
        moviesViewModel.returnPopularMovies().observe(HomeActivity.this, pagingResponse -> {

            homeBinding.lottie.setVisibility(View.GONE);

            if (pagingResponse != null && pagingResponse.getMovies().size() > 0) {

                movies.addAll(pagingResponse.getMovies());
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
        movies.addAll(response.getMovies());
        moviesAdapter = new MoviesAdapter(movies, this);
        homeBinding.rvMoviesList.setAdapter(moviesAdapter);

    }

    @Override
    public void onMovieClicked(int position) {

        logMovieClickEvent(position);

//        Navigate to details activity
        Intent intent = new Intent(HomeActivity.this, MovieDetailsActivity.class);
        intent.putExtra(getString(R.string.movie), movies.get(position));
        startActivity(intent);


    }

    private void logMovieClickEvent(int position) {

        params.putString("movie_name", movies.get(position).getTitle().trim());
        if (movies.get(position).getTitle().trim()
                .replaceAll(" ", "_")
                .replaceAll("(\\W|^_)*", "").length() > 40)
            analytics.logEvent(movies.get(position).getTitle().trim()
                    .replaceAll(" ", "_") //Replace any space with underscore
                    .replaceAll("(\\W|^_)*", "") //Remove any symbol
                    .substring(0, 40), params);
        else
            analytics.logEvent(movies.get(position).getTitle().trim()
                            .replaceAll(" ", "_") //Replace any space with underscore
                            .replaceAll("(\\W|^_)*", "") //Remove any symbol
                    , params);

    }

    @Override
    public void onLastMovieItemReached() {

//        Check if we didn't reach the movies end
        if (page < response.getTotalPages()) {
            homeBinding.lottie.setVisibility(View.VISIBLE);
            page++;
            moviesViewModel.getPopularMovies(page, getString(R.string.api_key));
        }

    }

    @Override
    public void onBackPressed() {
//        Exit app
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}