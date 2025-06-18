package com.sadiqov.weatherforecast.excebtion;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityAlreadyExitsException extends RuntimeException {

    public CityAlreadyExitsException(String message) {
        super(message);
    }
}
