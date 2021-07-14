package com.example.movies.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movies.R;
import com.example.movies.databinding.ActivityMovieDetailsBinding;
import com.example.movies.network.model.GetPopularMoviesResponse;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding movieDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsBinding = DataBindingUtil.setContentView(MovieDetailsActivity.this,
                R.layout.activity_movie_details);

        getIntentExtras();

    }

    private void getIntentExtras() {

        if (getIntent().getSerializableExtra(getString(R.string.movie)) != null)
            movieDetailsBinding.setMovie((GetPopularMoviesResponse.Result)
                    getIntent().getSerializableExtra(getString(R.string.movie)));

    }
}