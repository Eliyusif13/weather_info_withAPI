package com.sadiqov.weatherforecast.dto.request;

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
