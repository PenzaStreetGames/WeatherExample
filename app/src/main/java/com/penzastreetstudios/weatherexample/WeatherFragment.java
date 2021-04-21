package com.penzastreetstudios.weatherexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment {

    public TextView cloudField;
    public TextView tempField;
    public TextView dateField;
    public TextView partField;

    public Weather weather;

    WeatherFragment thisFragment = this;

    public WeatherFragment(Weather weather) {
        this.weather = weather;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_widget, container, false);
        cloudField = view.findViewById(R.id.cloud);
        tempField = view.findViewById(R.id.temperature);
        dateField = view.findViewById(R.id.date);
        partField = view.findViewById(R.id.part);

        updateView();

        return view;
    }

    public void updateView() {
        if (weather != null) {
            cloudField.setText(weather.cloud);
            tempField.setText(weather.temp.replace("&minus;", "-"));
            dateField.setText(weather.date);
            partField.setText(weather.tod);

        }
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
