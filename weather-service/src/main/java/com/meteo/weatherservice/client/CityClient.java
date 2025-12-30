package com.meteo.weatherservice.client;

import com.meteo.weatherservice.model.CityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "city-service")
public interface CityClient {

    @GetMapping("/cities/by-name/{name}")
    CityDTO getCityByName(@PathVariable("name") String name);
}

