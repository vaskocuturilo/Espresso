package com.example.espresso.service;

import com.example.espresso.model.schema.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String city, @Query("appid") String app_id);
}
