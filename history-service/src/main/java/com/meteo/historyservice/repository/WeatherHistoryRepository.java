package com.meteo.historyservice.repository;

import com.meteo.historyservice.model.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WeatherHistoryRepository
        extends JpaRepository<WeatherHistory, Long> {

    List<WeatherHistory> findByCity(String city);
}
