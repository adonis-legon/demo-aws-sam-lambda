package com.example.business;

import com.example.exceptions.WeatherException;
import com.example.model.Weather;

public interface WeatherService {
    Weather getWeatherFromIp(String ipAddress) throws WeatherException;
}
