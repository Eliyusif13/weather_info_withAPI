package com.sadiqov.weatherforecast.dto;

import com.sadiqov.weatherforecast.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {
    private String cityName;
    private Double temperature;
    private Integer humidity;
    private String description;
    private LocalDateTime timestamp;
}
