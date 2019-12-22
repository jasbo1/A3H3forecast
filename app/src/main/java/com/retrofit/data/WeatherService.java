package com.retrofit.data;

import com.example.CurrentWeather;
import com.retrofit.data.entity.ForecastEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.retrofit.data.ApiEndpoins.CURRENT_WEATHER;
import static com.retrofit.data.ApiEndpoins.FORECAST_WEATHER;

public interface WeatherService {

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> currentWeather(@Query("q") String city,
                                        @Query("appid") String key,
                                        @Query("units") String metric);

@GET (FORECAST_WEATHER)
Call<ForecastEntity> forecastEntity(@Query("q") String city,
                                    @Query("appid") String key,
                                    @Query("units") String metric);
}