package com.example.movies.network.model;

import com.example.movies.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetPopularMoviesResponse implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<Movie> getMovies() {
        return movies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

}
