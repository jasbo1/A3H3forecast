package com.retrofit.data;

import com.example.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<CurrentWeather> currentWeather(@Query("q") String city,
                                        @Query("appid") String key,
                                        @Query("units") String metric);
}
