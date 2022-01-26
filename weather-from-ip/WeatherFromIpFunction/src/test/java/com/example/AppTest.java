package com.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.tests.annotations.Events;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;

public class AppTest {
  @ParameterizedTest
  @Events(folder = "events", type = APIGatewayProxyRequestEvent.class)
  public void givenSampleValidRequest_return200WithWeatherInfo(APIGatewayProxyRequestEvent requestEvent) throws IOException {
    
    APIGatewayProxyResponseEvent result = new App().handleRequest(requestEvent, null);
    assertEquals(200, result.getStatusCode().intValue());
    
    JsonObject jsonObj = JsonParser.parseString(result.getBody()).getAsJsonObject().getAsJsonObject();
    assertNotNull(jsonObj.get("weather"));
    assertTrue(jsonObj.get("weather").getAsJsonArray().size() > 0);
  }
}
