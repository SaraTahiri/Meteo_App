package com.meteo.historyservice.controller;

import com.meteo.historyservice.model.WeatherHistory;
import com.meteo.historyservice.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @PostMapping
    public WeatherHistory save(@RequestBody WeatherHistory req) {

        return service.save(req.getCity(), req.getTemperature(),req.getCondition());
    }

    @GetMapping("/{city}")
    public List<WeatherHistory> getHistory(@PathVariable String city) {
        return service.historyByCity(city);
    }
}
