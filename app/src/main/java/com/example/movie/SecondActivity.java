package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.classes.DetailMovie;
import com.example.movie.databinding.ActivitySecondBinding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    Retrofit retrofit;
    ListMovieAPI api;
    private ActivitySecondBinding binding;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        binding = DataBindingUtil.setContentView(SecondActivity.this, R.layout.activity_second);
        poster = findViewById(R.id.poster);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ListMovieAPI.class);
        getDetailsMovie();
    }

    private void getDetailsMovie() {
        Call<DetailMovie> call = api.getDetailsMovie("https://www.omdbapi.com/?apikey=3e974fca&i=" + MainActivity.url);
        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SecondActivity.this, "can't load :/", Toast.LENGTH_SHORT).show();
                    return;
                }
                DetailMovie detailMovie = response.body();
                Picasso.get().load(detailMovie.getPoster()).into(poster);
                binding.setMovie(detailMovie);
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Toast.makeText(SecondActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}