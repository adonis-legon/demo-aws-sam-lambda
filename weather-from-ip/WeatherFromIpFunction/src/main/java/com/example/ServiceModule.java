package com.example;

import com.example.business.PublicIpApiService;
import com.example.business.PublicIpService;
import com.example.business.WeatherApiService;
import com.example.business.WeatherService;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(WeatherService.class).to(WeatherApiService.class);
        bind(PublicIpService.class).to(PublicIpApiService.class);
      }
}
