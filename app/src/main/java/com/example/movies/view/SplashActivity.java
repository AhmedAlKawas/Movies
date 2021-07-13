package com.example.movies.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.view_model.MoviesViewModel;

public class SplashActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initListeners();

        moviesViewModel.getPopularMovies(page, getString(R.string.api_key));

    }

    private void initListeners() {

        moviesViewModel = new ViewModelProvider(SplashActivity.this)
                .get(MoviesViewModel.class);
        moviesViewModel.returnPopularMovies().observe(SplashActivity.this, response -> {

            if (response != null) {


            }

        });

    }
}