package com.sadiqov.weatherforecast.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double temperature;
    int humidity;
    String description;
    LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
