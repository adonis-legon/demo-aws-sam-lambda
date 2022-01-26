package com.example.business;

import com.example.exceptions.PublicIpException;

public interface PublicIpService {
    String[] getGeoLocationFromIp(String ipAddress) throws PublicIpException;
}
