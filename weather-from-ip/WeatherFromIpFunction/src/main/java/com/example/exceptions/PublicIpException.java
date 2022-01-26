package com.example.exceptions;

public class PublicIpException extends Exception {
    public PublicIpException(String message, Exception e) {
        super(message, e);
    }
}
