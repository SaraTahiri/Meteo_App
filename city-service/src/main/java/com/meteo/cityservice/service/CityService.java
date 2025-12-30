package com.meteo.cityservice.service;

import com.meteo.cityservice.model.City;
import com.meteo.cityservice.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City updateCity(Long id, City city) {
        City existing = getCityById(id);
        if (existing != null) {
            existing.setName(city.getName());
            existing.setCountry(city.getCountry());
            return cityRepository.save(existing);
        }
        return null;
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }

}
