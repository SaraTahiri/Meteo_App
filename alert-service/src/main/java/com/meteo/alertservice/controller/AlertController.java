package com.meteo.alertservice.controller;

import com.meteo.alertservice.model.AlertDTO;
import com.meteo.alertservice.service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping("/{city}")
    public List<AlertDTO> getAlerts(@PathVariable String city) {
        return service.generateAlerts(city);
    }
}
