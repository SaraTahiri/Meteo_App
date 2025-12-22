package com.meteo.historyservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
public class WeatherHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double temperature;
    private String condition;
    private LocalDateTime timestamp;

    public WeatherHistory() {}

    public WeatherHistory(String city, double temperature, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.timestamp = LocalDateTime.now();
    }

    // GETTERS et SETTERS OBLIGATOIRES
    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


}
