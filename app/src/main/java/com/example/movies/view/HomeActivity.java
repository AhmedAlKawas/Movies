package com.example.movies.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movies.R;
import com.example.movies.databinding.ActivityHomeBinding;
import com.example.movies.network.model.GetPopularMoviesResponse;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding homeBinding;
    private GetPopularMoviesResponse response;

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

    }
}