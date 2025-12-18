package com.meteo.weatherservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "openweather-client", url = "${weather.api.url}")
public interface ExternalWeatherClient {

    @GetMapping("/weather")
    Map<String, Object> getWeather(
            @RequestParam("q") String city,
            @RequestParam("appid") String apiKey,
            @RequestParam("units") String units
    );
}
