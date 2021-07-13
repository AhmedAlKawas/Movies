package com.example.movies.network.services;

import com.example.movies.network.model.GetPopularMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesServices {

    @GET("movie/popular")
    Observable<GetPopularMoviesResponse> getPopularMovies(@Query("page") int page,
                                                          @Query("api_key") String apiKey);

}
