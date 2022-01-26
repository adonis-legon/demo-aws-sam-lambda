package com.example.business;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.exceptions.WeatherException;
import com.example.model.Weather;
import com.google.inject.Inject;

public class WeatherApiService implements WeatherService {
    private HttpClient httpClient;

    private PublicIpService publicIpService;

    @Inject
    public WeatherApiService(PublicIpService publicIpService) {
        this.httpClient = HttpClient.newHttpClient();
        this.publicIpService = publicIpService;
    }

    @Override
    public Weather getWeatherFromIp(String ipAddress) throws WeatherException {
        try {
            String[] geoLocation = this.publicIpService.getGeoLocationFromIp(ipAddress);
            if(geoLocation == null || geoLocation.length != 2 || geoLocation[0].equalsIgnoreCase("Undefined")){
                throw new Exception("There is no GeoLocation identified for the source IP: " + ipAddress);
            }

            String weatherApiUrl = String.format("%s?lat=%s&lon=%s&appid=%s", System.getenv("WEATHER_API_BASE_URL"),
                    geoLocation[0], geoLocation[1], System.getenv("WEATHER_API_KEY"));

            HttpRequest request = HttpRequest.newBuilder().uri(new URI(weatherApiUrl)).GET().build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return Weather.parseJson(response.body());
        } catch (Exception e) {
            throw new WeatherException("Error getting Weather Forcast from IP Address: " + ipAddress, e);
        }
    }
}
