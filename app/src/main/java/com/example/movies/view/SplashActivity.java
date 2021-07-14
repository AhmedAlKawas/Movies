package com.example.movies.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.view_model.MoviesViewModel;

public class SplashActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initListeners();

        moviesViewModel.getPopularMovies(1, getString(R.string.api_key));

    }

    private void initListeners() {

        moviesViewModel = new ViewModelProvider(SplashActivity.this)
                .get(MoviesViewModel.class);
        moviesViewModel.returnPopularMovies().observe(SplashActivity.this, response -> {

            if (response != null) {

                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                intent.putExtra(getString(R.string.response), response);
                startActivity(intent);

            }

        });

    }
}