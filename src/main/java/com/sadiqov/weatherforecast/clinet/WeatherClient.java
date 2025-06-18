package com.sadiqov.weatherforecast.clinet;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadiqov.weatherforecast.dto.WeatherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDTO getWeather(String cityName) {
        String API_KEY = "77d09da501b4123619e80ae0cee2f8ec";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                "&appid=" + API_KEY + "&units=metric";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode body = response.getBody();

        WeatherDTO dto = new WeatherDTO();
        dto.setTemperature(body.get("main").get("temp").asDouble());
        dto.setHumidity(body.get("main").get("humidity").asInt());
        dto.setDescription(body.get("weather").get(0).get("description").asText());
        return dto;
    }
}
