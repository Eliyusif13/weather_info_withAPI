package com.sadiqov.weatherforecast.service;

import com.sadiqov.weatherforecast.clinet.WeatherClient;
import com.sadiqov.weatherforecast.dto.request.WeatherDTO;
import com.sadiqov.weatherforecast.entity.City;
import com.sadiqov.weatherforecast.entity.Weather;
import com.sadiqov.weatherforecast.repository.CityRepository;
import com.sadiqov.weatherforecast.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final CityRepository cityRepo;
    private final WeatherRepository weatherRepo;
    private final WeatherClient weatherClient;

    @Cacheable(value = "weather", key = "#city.name")
    public void getWeatherFromCache(City city) {
        weatherClient.getWeather(city.getName());
    }

    @CachePut(value = "weather", key = "#city.name")
    public WeatherDTO updateWeatherCache(City city) {
        WeatherDTO dto = weatherClient.getWeather(city.getName());

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setTemperature(dto.getTemperature());
        weather.setHumidity(dto.getHumidity());
        weather.setDescription(dto.getDescription());
        weather.setTimestamp(LocalDateTime.now());
        weatherRepo.save(weather);

        return dto;
    }

    @Scheduled(fixedRate = 3600000)
    public void updateWeatherForAllCities() {
        List<City> cities = cityRepo.findAll();
        for (City city : cities) {
            updateWeatherCache(city);
        }
    }

    @PostConstruct
    public void init() {
        List<City> cities = cityRepo.findAll();
        for (City city : cities) {
            getWeatherFromCache(city);
        }
    }
}

