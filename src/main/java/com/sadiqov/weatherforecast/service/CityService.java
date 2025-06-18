package com.sadiqov.weatherforecast.service;

import com.sadiqov.weatherforecast.dto.CityDto;
import com.sadiqov.weatherforecast.entity.City;
import com.sadiqov.weatherforecast.excebtion.CityAlreadyExitsException;
import com.sadiqov.weatherforecast.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public ResponseEntity<?> saveCity(CityDto cityDto){

        if (cityRepository.findByName(cityDto.getName()).isPresent()) {
            throw new CityAlreadyExitsException(cityDto.getName()+" adi ile artiq seher elave olunub..");
        }
        City city = new City();
        city.setCountry(cityDto.getCountry());
        city.setName(cityDto.getName());
        city.setCountryCode(cityDto.getCountryCode());
        return ResponseEntity.ok(cityRepository.save(city));
    }
}
