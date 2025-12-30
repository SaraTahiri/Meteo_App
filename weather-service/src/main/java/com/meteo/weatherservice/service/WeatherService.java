package com.meteo.weatherservice.service;
import com.meteo.weatherservice.client.CityClient;
import com.meteo.weatherservice.client.OpenWeatherClient;
import com.meteo.weatherservice.model.CityDTO;
import com.meteo.weatherservice.model.WeatherDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
@Service
public class WeatherService {

    private final OpenWeatherClient weatherClient;
    private final CityClient cityClient;
    private final String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();// to send rquests to other services
    private final String HISTORY_SERVICE_URL = "http://localhost:8084/history";// to comminucate to history_service

    public WeatherService(OpenWeatherClient weatherClient,
                          CityClient cityClient,
                          @Value("${weather.api.key}") String apiKey) {
        this.weatherClient = weatherClient;
        this.cityClient = cityClient;
        this.apiKey = apiKey;
    }

    @CircuitBreaker(name = "weatherService", fallbackMethod = "fallbackWeather")
    public WeatherDTO getWeather(String cityName) {

        // 1️⃣ Vérifier si la ville existe (API interne)
        CityDTO city = cityClient.getCityByName(cityName);
        if (city == null) {
            throw new RuntimeException("City not found: " + cityName);
        }

        // 2️⃣ Appel API externe pour récupérer la météo
        Map<String, Object> response = weatherClient.getWeather(cityName, apiKey, "metric");

        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
        Map<String, Object> mainMap = (Map<String, Object>) response.get("main");

        String mainWeather = weatherList.get(0).get("main").toString();
        String description = weatherList.get(0).get("description").toString();
        String condition = mainWeather + " (" + description + ")";
        double temp = Double.parseDouble(mainMap.get("temp").toString());

        // 3️⃣ Créer le DTO météo
        WeatherDTO weatherDTO = new WeatherDTO(cityName, temp, condition);

        // 4️⃣ Envoyer la météo vers le History Service
        try {
            restTemplate.postForObject(HISTORY_SERVICE_URL, weatherDTO, WeatherDTO.class);
        } catch (Exception e) {
            System.out.println("⚠️ Impossible d'envoyer la météo au History Service : " + e.getMessage());
        }

        // 5️⃣ Retourner le DTO
        return weatherDTO;
    }

    public WeatherDTO fallbackWeather(String city, Throwable t) {
        return new WeatherDTO(city, 25, "Unavailable (Fallback)");
    }
}

