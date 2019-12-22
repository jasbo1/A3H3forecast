package com.retrofit.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.CurrentWeather;
import com.retrofit.R;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastHolder> {

    private List<CurrentWeather> weather = new ArrayList<>();

    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_forecast_weather, parent, false);
        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        holder.onBind(weather.get(position));

    }

    public void updateWeather (List<CurrentWeather> forecastWeather) {
        this.weather = forecastWeather;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return weather.size();
    }
}
