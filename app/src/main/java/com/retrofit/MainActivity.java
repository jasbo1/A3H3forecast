package com.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.CurrentWeather;
import com.example.Main;
import com.retrofit.data.RetrofitBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    TextView textView, textViewToday1, textViewToday2, humidity, wind, pressure, cloudness, tvDay, tvMonth, tvYear, sunrise;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.nowTemp);
        textViewToday1 = findViewById(R.id.todayTemp);
        textViewToday2 = findViewById(R.id.todayTemp2);
        humidity = findViewById(R.id.humidity_data);
        wind = findViewById(R.id.wind_data);
        pressure = findViewById(R.id.pressure_data);
        imageView = findViewById(R.id.imageView);
        cloudness = findViewById(R.id.cloudiness_data);
        tvDay = findViewById(R.id.tvDay);
        tvMonth = findViewById(R.id.tvMonth);
        tvYear = findViewById(R.id.tvYear);
        sunrise = findViewById(R.id.sunrise_data);


        fetchWeather();
        calendarFormat();
    }

    private void calendarFormat() {

        Calendar calendar1 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        String formatted_day = sdf1.format(calendar1.getTime());
        tvDay.setText(formatted_day);

        Calendar calendar2 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM");
        String formatted_month = sdf2.format(calendar2.getTime());
        tvMonth.setText(formatted_month);

        Calendar calendar3 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf3 = new SimpleDateFormat("YYYY");
        String formatted_year = sdf3.format(calendar3.getTime());
        tvYear.setText(formatted_year);
    }

    private void fetchWeather() {
        RetrofitBuilder.getService()
                .currentWeather("Bishkek", getResources()
                        .getString(R.string.weather_key), "metric")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            textView.setText(response.body().getMain().getTemp().toString());
                            textViewToday2.setText(response.body().getMain().getTempMax().toString());
                            textViewToday2.setText(response.body().getMain().getTempMin().toString());
                            humidity.setText(response.body().getMain().getHumidity().toString());
                            pressure.setText(response.body().getMain().getPressure().toString() + "mb");
                            cloudness.setText(response.body().getClouds().toString());
                            wind.setText(response.body().getWind().getSpeed().toString());

                            Glide.with(MainActivity.this).load("http://openweathermap.org/img/wn/" + response.body().
                                    getWeather().get(0).getIcon() + "@2x.png").into(imageView);
                        }

                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);

                    }
                });
    }
}
