package com.meteo.weatherservice.controller;

import com.meteo.weatherservice.model.WeatherDTO;
import com.meteo.weatherservice.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/{city}")
    public WeatherDTO getWeather(@PathVariable String city) {
        return service.getWeather(city);
    }
}
