package com.meteo.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO {
    private String city;
    private double temperature;
    private String condition;

    public WeatherDTO() {}

    public WeatherDTO(String city, double temperature, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
    }

    // GETTERS et SETTERS OBLIGATOIRES
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Optionnel : toString() pour le débogage
    @Override
    public String toString() {
        return "WeatherDTO{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", condition='" + condition + '\'' +
                '}';
    }
}