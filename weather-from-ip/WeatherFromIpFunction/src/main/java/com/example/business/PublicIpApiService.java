package com.example.business;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.exceptions.PublicIpException;
import com.google.inject.Inject;

public class PublicIpApiService implements PublicIpService{
    private HttpClient httpClient;
    
    @Inject
    public void PublicIpAPIService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String[] getGeoLocationFromIp(String ipAddress) throws PublicIpException{
        try {
            String publicIpApiUrl = String.format("%s/%s/latlong", System.getenv("PUBLIC_IP_API_BASE_URL"), ipAddress);
        
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(publicIpApiUrl)).GET().build();
            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    
            return response.body().split(",");
        } catch (Exception e) {
            throw new PublicIpException("Error getting GeoLocation from Public IP: " + ipAddress, e);
        }
    }
}
