package com.meteo.alertservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "weather-service")
public interface WeatherClient {

    @GetMapping("/weather/{city}")
    Map<String, Object> getWeather(@PathVariable("city") String city);
}