package com.example.movies.repository;

import com.example.movies.network.model.GetPopularMoviesResponse;
import com.example.movies.network.services.MoviesServices;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepo {

    private MoviesServices moviesServices;

    public MoviesRepo() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String BASE_URL = "https://salanseh.com/wp-json/wp/v2/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        moviesServices = retrofit.create(MoviesServices.class);

    }

    public static MoviesRepo getInstance() {
        return Loader.moviesRepo;
    }

    private static class Loader {
        static MoviesRepo moviesRepo = new MoviesRepo();
    }

    public Observable<GetPopularMoviesResponse> getPopularMovies(int page, String apiKey) {

        return Observable.create(emitter -> {

            moviesServices.getPopularMovies(page, apiKey).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io()).subscribe(new Observer<GetPopularMoviesResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull GetPopularMoviesResponse response) {
                    emitter.onNext(response);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    emitter.onError(e);
                }

                @Override
                public void onComplete() {

                }
            });

        });

    }

}
