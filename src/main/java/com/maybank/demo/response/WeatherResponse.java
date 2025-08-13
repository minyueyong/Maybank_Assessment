package com.maybank.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherResponse {

    private Hourly hourly;



    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;


    }
}
