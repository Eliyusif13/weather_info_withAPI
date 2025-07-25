package com.sadiqov.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastApplication.class, args);
    }

}
