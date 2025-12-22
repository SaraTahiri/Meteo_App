package com.meteo.historyservice.service;

import com.meteo.historyservice.model.WeatherHistory;
import com.meteo.historyservice.repository.WeatherHistoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistoryService {

    private final WeatherHistoryRepository repository;

    public HistoryService(WeatherHistoryRepository repository) {
        this.repository = repository;
    }

    public WeatherHistory  save (String city, double temp, String condition) {

        return repository.save(new WeatherHistory(city, temp, condition));

    }

    public List<WeatherHistory> historyByCity(String city) {
        return repository.findByCity(city);
    }
}

