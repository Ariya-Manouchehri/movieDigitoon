package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListMovieAPI api;
    Retrofit retrofit;
    RecyclerView recyclerview;
    ListMovieAdapter listMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recyclerview);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ListMovieAPI.class);

        getListMovie();

    }

    private void getListMovie() {

        Call<ListMove> call = api.getAllMovies("?apikey=3e974fca&s=batman");

        call.enqueue(new Callback<ListMove>() {
            @Override
            public void onResponse(Call<ListMove> call, Response<ListMove> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "can't load :/", Toast.LENGTH_SHORT).show();
                    return;
                }
                ListMove listMove = response.body();
                if (!listMove.getSearch().isEmpty()) {
                    listMovieAdapter = new ListMovieAdapter(listMove.getSearch(), MainActivity.this);
                }

                recyclerview.setAdapter(listMovieAdapter);
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<ListMove> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}