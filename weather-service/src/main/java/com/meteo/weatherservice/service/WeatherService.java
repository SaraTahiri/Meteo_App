package com.meteo.weatherservice.service;
import com.meteo.weatherservice.client.OpenWeatherClient;
import com.meteo.weatherservice.model.WeatherDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class WeatherService {
    private final OpenWeatherClient client;
    private final String apiKey;
    public WeatherService(OpenWeatherClient client,
                          @Value("${weather.api.key}") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }
    @CircuitBreaker(name = "weatherService", fallbackMethod = "fallbackWeather")
    public WeatherDTO getWeather(String city) {
        // Appel API
        Map<String, Object> response = client.getWeather(city, apiKey, "metric");
        // Récupérer la météo principale (ex: Clear, Rain, etc.)
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
        String mainWeather = "";
        String description = "";
        if (weatherList != null && !weatherList.isEmpty()) {
            Map<String, Object> weatherMap = weatherList.get(0);
            mainWeather = weatherMap.get("main").toString();
            description = weatherMap.get("description").toString();
        }
        // Récupérer la température
        Map<String, Object> mainMap = (Map<String, Object>) response.get("main");
        double temp = 0;
        if (mainMap != null && mainMap.get("temp") != null) {
            temp = Double.parseDouble(mainMap.get("temp").toString());
        }
        // Retourner un DTO
        return new WeatherDTO(city, temp, mainWeather + " (" + description + ")");
    }
    // Fallback si API indisponible
    public WeatherDTO fallbackWeather(String city, Throwable t) {
        return new WeatherDTO(city, 25, "Unavailable (Fallback)");
    }
}
