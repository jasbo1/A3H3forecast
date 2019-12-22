package com.retrofit.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.CurrentWeather;
import com.retrofit.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.forecast_data)
    TextView data;
    @BindView(R.id.forecastMax)
    TextView forecastmaxTemp;
    @BindView(R.id.forecastMin)
    TextView forecastMinTemp;
    @BindView(R.id.forecastSun)
    ImageView forecastImage;

    public ForecastHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(CurrentWeather forecastEntity) {

        try {
            data.setText(parseDate(forecastEntity.getData_txt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        forecastmaxTemp.setText((forecastEntity.getMain().getTempMax().toString()));
        forecastMinTemp.setText((forecastEntity.getMain().getTempMin().toString()));
      /*  Picasso.get().load("https://www.openweathermap.org/img/w/" + forecastEntity.getWeather()
                .get(0).getIcon() + ".png").into(imgForecastIcon);*/
       /* Glide.with(this).load("http://openweathermap.org/img/wn/" + forecastEntity.
                getWeather().get(0).getIcon()+"@2x.png").into(forecastImage);*/

    }

    private String parseDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Date date1 = dateFormat.parse(date);

        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MMM ", Locale.ENGLISH);
        String newDate = newDateFormat.format(date1);
        return newDate;
    }
}

