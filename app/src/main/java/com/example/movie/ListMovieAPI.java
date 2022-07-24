package com.example.movie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ListMovieAPI {

    @GET()
    Call<ListMove> getAllMovies(@Url String url);
}
