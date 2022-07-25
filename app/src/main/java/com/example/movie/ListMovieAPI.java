package com.example.movie;

import com.example.movie.classes.DetailMovie;
import com.example.movie.classes.ListMove;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ListMovieAPI {

    @GET()
    Call<ListMove> getAllMovies(@Url String url);

    @GET()
    Call<DetailMovie> getDetailsMovie(@Url String url);
}
