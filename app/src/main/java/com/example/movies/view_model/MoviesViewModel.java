package com.example.movies.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.network.model.GetPopularMoviesResponse;
import com.example.movies.repository.MoviesRepo;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MoviesViewModel extends ViewModel {

    //    get instance of movies repository
    MoviesRepo moviesRepo = MoviesRepo.getInstance();

    private MutableLiveData<GetPopularMoviesResponse> getPopularMoviesLiveData =
            new MutableLiveData<>();

    public MutableLiveData<GetPopularMoviesResponse> returnPopularMovies() {
        return getPopularMoviesLiveData;
    }

    public void getPopularMovies(int page, String apiKey) {

        moviesRepo.getPopularMovies(page, apiKey).subscribe(new Observer<GetPopularMoviesResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull GetPopularMoviesResponse response) {
                getPopularMoviesLiveData.postValue(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getPopularMoviesLiveData.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
