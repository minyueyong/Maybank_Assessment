package com.maybank.demo.service;

import com.maybank.demo.response.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    Logger logger = LoggerFactory.getLogger(WeatherService.class);

    RestTemplate restTemplate = new RestTemplate();

    public Map<String, Double> getTemperature(double latitude, double longitude) {


        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m",
                latitude, longitude);


        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);


        List<String> times = response.getHourly().getTime();
        List<Double> temps = response.getHourly().getTemperature_2m();

        Map<String, Double> map = new LinkedHashMap<>();
        for (int i = 0; i < times.size(); i++) {
            map.put(times.get(i), temps.get(i));
        }
        return map;
    }
}
