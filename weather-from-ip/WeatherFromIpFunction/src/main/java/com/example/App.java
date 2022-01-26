package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.business.WeatherService;
import com.example.model.Weather;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private WeatherService weatherService;

    public App() {
        Injector injector = Guice.createInjector(new ServiceModule());
        this.weatherService = injector.getInstance(WeatherService.class);
    }

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            String sourceIpAddress = input.getHeaders().get("X-Forwarded-For").split(",")[0];
            Weather weather = weatherService.getWeatherFromIp(sourceIpAddress);

            return response.withStatusCode(200).withBody(weather.toString());
        } catch (Exception e) {
            return response.withBody(e.getMessage()).withStatusCode(500);
        }
    }
}
