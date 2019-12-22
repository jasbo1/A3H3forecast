package com.retrofit.data.entity;

import com.example.CurrentWeather;

import java.util.List;

public class ForecastEntity {
    private List<CurrentWeather> list;


    public List<CurrentWeather> getList() {
        return list;
    }

    public void setList(List<CurrentWeather> list) {
        this.list = list;
    }
}
