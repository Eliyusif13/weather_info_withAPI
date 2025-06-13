package com.sadiqov.weatherforecast.service;

import com.sadiqov.weatherforecast.clinet.WeatherClient;
import com.sadiqov.weatherforecast.dto.WeatherDTO;
import com.sadiqov.weatherforecast.entity.City;
import com.sadiqov.weatherforecast.entity.Weather;
import com.sadiqov.weatherforecast.repository.CityRepository;
import com.sadiqov.weatherforecast.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final CityRepository cityRepo;
    private final WeatherRepository weatherRepo;
    private final WeatherClient weatherClient;

    public void fetchAndSaveWeather(City city) {

        WeatherDTO dto = weatherClient.getWeather(city.getName());

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setTemperature(dto.getTemperature());
        weather.setHumidity(dto.getHumidity());
        weather.setDescription(dto.getDescription());
        weather.setTimestamp(LocalDateTime.now());
        weatherRepo.save(weather);
    }

    @Scheduled(fixedRate = 3600000)
    public void updateWeatherForAllCities() {
        List<City> cities = cityRepo.findAll();
        cities.forEach(this::fetchAndSaveWeather);
    }


}
