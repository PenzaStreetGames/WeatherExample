package com.penzastreetstudios.weatherexample;

public class Weather {
    public String date;
    public String tod;
    public String pressure;
    public String temp;
    public String humidity;
    public String wind;
    public String cloud;

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", tod='" + tod + '\'' +
                ", pressure='" + pressure + '\'' +
                ", temp='" + temp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", cloud='" + cloud + '\'' +
                '}';
    }
}
