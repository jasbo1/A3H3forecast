package com.retrofit.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.CurrentWeather;
import com.example.Main;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.retrofit.R;
import com.retrofit.data.RetrofitBuilder;
import com.retrofit.data.entity.ForecastEntity;
import com.retrofit.ui.base.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.retrofit.BuildConfig.API_KEY;


public class MainActivity extends BaseActivity {


    @BindView(R.id.celsiusNow)
    TextView celsiusNow;
    @BindView(R.id.maxTemp)
    TextView maxTemp;
    @BindView(R.id.minTemp)
    TextView minTemp;
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tvMonth)
    TextView tvMonth;
    @BindView(R.id.tvYear)
    TextView tvYear;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.imageSunny)
    ImageView imageSunny;
    @BindView(R.id.showLocation)
    ImageView showLocation;
   /* @BindView(R.id.sunrise_data)
    TextView sunrise;
    @BindView(R.id.sunset_data)
    TextView sunset;*/
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchWeather();
        calendarFormat();
        fetchForecast();
        initRecylerView();
        initForecastAdapter();
    }
    public void  initRecylerView(){
        recyclerView = findViewById(R.id.recyclerView);
    

    }
    public void initForecastAdapter() {
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void showForecastWeather(List<CurrentWeather> weather) {
        adapter.updateWeather(weather);
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
    public String parseDateToTime(double time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        date.setTime((long) time * 1000);
        return dateFormat.format(date.getTime());
    }
    private void fetchWeather() {
        RetrofitBuilder.getService()
                .currentWeather("Bishkek", getResources()
                        .getString(R.string.weather_key), "metric")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            filldata(response.body());
                        }
                    }


                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        toast(t.getLocalizedMessage());

                    }
                });
    }

    private void fetchForecast() {
        RetrofitBuilder.getService()
                .forecastEntity("Bishkek", API_KEY, "metric")
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            toast(response.code() + "");
                            showForecastWeather(response.body().getList());

                        }

                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        toast(t.getLocalizedMessage());

                    }
                });
    }



    private void filldata(CurrentWeather body) {

        celsiusNow.setText(body.getMain().getTemp().toString() + "ºC");
        maxTemp.setText(body.getMain().getTempMax().toString());
        minTemp.setText(body.getMain().getTempMin().toString());
        wind.setText(body.getWind().getSpeed().toString());
       /*sunrise.setText(parseDateToTime(body.getSys().getSunrise()));
       sunset.setText(parseDateToTime(body.getSys().getSunset()));*/
      /* humidity humidity.setText(body.getMain().getHumidity().toString());
        pressure.setText(body.getMain().getPressure().toString() + "mb");
        cloudness.setText(body.getClouds().getAll().toString());
       */

        Glide.with(MainActivity.this).load("http://openweathermap.org/img/wn/" + body.
                getWeather().get(0).getIcon()+"@2x.png").into(imageSunny);

    }
}
