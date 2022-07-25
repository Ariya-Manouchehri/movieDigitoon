package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.movie.classes.DetailMovie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    Retrofit retrofit;
    ListMovieAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ListMovieAPI.class);
        getDetailsMovie();
    }

    private void getDetailsMovie() {
        Call<DetailMovie> call = api.getDetailsMovie("https://www.omdbapi.com/?apikey=3e974fca&i="+getIntent().getStringExtra("imdbID"));
        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SecondActivity.this, "can't load :/", Toast.LENGTH_SHORT).show();
                    return;
                }
                DetailMovie detailMovie = response.body();
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Toast.makeText(SecondActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}