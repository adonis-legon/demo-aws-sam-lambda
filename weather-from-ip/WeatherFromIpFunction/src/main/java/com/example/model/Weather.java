package com.example.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {
    private JsonElement rootNode;

    public static Weather parseJson(String json){
        return new Weather(JsonParser.parseString(json).getAsJsonObject());
    }

    @Override
    public String toString() {
        return rootNode.toString();
    }

}
