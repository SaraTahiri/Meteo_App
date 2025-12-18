package com.meteo.cityservice.repositories;

import com.meteo.cityservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
}
