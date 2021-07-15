package com.example.movies.model;

import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movies.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;

    @BindingAdapter("loadImage")
    public static void loadImageByGlide(ImageView imageView, String imgUrl) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_baseline_movie_24)
                .error(R.drawable.ic_baseline_movie_24)
                .centerCrop();

        Glide.with(imageView.getContext()).setDefaultRequestOptions(requestOptions)
                .load(imageView.getContext().getString(R.string.image_base_url) + imgUrl)
                .into(imageView);
    }

    @BindingAdapter("setRatingDividedByTwo")
    public static void setRating(AppCompatRatingBar ratingBar, Float rating) {
        ratingBar.setRating(rating / 2);
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

}
