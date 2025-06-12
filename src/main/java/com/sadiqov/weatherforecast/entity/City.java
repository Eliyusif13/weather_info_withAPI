package com.sadiqov.weatherforecast.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String country;
    String countryCode;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    List<Weather> weathers;
}
