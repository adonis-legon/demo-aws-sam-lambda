package com.example.exceptions;

public class WeatherException extends Exception{
    public WeatherException(String message, Exception ex) {
        super(message, ex);
    }
}
