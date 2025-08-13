package com.maybank.demo.controller;

import com.maybank.demo.dto.WeatherRequest;
import com.maybank.demo.service.WeatherService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;

    public WeatherController(WeatherService currencyService) {
        this.weatherService = currencyService;
    }

    @GetMapping("/temperature")
    public ResponseEntity<?> getHourlyTemperature( @Valid @RequestBody WeatherRequest request) {

        Map<String, Double> hourlyData =  weatherService.getTemperature(request.getLatitude(), request.getLongitude());


        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("hourly", hourlyData);

        return ResponseEntity.ok(response);

    }
}
