package com.example.movies.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemMovieBinding;
import com.example.movies.interfaces.MovieCallback;
import com.example.movies.model.Movie;
import com.example.movies.network.model.GetPopularMoviesResponse;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private final List<Movie> moviesList;
    private LayoutInflater layoutInflater;
    private MovieCallback callback;

    public MoviesAdapter(List<Movie> moviesList, MovieCallback callback) {
        this.moviesList = moviesList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding movieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie,
                parent, false);
        return new MovieHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.movieBinding.setMovie(moviesList.get(position));
        holder.movieBinding.cvMovieItem.setOnClickListener(view -> callback.onMovieClicked(position));

        if (position == moviesList.size() - 1) {
            callback.onLastMovieItemReached();
        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    static class MovieHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding movieBinding;

        MovieHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            movieBinding = binding;
        }
    }

}
