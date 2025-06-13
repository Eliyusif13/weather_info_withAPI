package com.sadiqov.weatherforecast.repository;

import com.sadiqov.weatherforecast.entity.City;
import com.sadiqov.weatherforecast.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {
    List<Weather> findTop24ByCityOrderByTimestampDesc(City city);
}
