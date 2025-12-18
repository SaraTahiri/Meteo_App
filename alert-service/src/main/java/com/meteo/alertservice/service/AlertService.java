package com.meteo.alertservice.service;


import com.meteo.alertservice.client.WeatherClient;
import com.meteo.alertservice.model.AlertDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {

    private final WeatherClient weatherClient;

    public AlertService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public List<AlertDTO> generateAlerts(String city) {

        Map<String, Object> weather = weatherClient.getWeather(city);

        double temperature = Double.parseDouble(
                weather.get("temperature").toString()
        );

        String condition = weather.get("condition").toString();

        List<AlertDTO> alerts = new ArrayList<>();

        if (temperature >= 35) {
            alerts.add(new AlertDTO(
                    "CANICULE",
                    "Température très élevée à " + city
            ));
        }

        if (temperature <= 5) {
            alerts.add(new AlertDTO(
                    "FROID",
                    "Température très basse à " + city
            ));
        }

        if (condition.toLowerCase().contains("rain")) {
            alerts.add(new AlertDTO(
                    "PLUIE",
                    "Risque de pluie à " + city
            ));
        }

        return alerts;
    }
}
