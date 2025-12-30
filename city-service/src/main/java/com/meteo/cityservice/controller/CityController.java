package com.meteo.cityservice.controller;
import com.meteo.cityservice.model.City;
import com.meteo.cityservice.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public City create(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @GetMapping
    public List<City> getAll() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.deleteCity(id);
    }

    @GetMapping("/by-name/{name}")
    public City getByName(@PathVariable String name) {
        return cityService.getCityByName(name);
    }

}


