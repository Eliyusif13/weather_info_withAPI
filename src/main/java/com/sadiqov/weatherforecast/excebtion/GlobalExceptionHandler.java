package com.sadiqov.weatherforecast.excebtion;
import com.sadiqov.weatherforecast.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserCodeAlreadyExistsException.class)
    public ResponseEntity<CommonResponse<?>> handleUserCodeAlreadyExistsException(UserCodeAlreadyExistsException alreadyExistsException) {
        return new ResponseEntity<>(alreadyExistsException.getResponseDTO(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CityAlreadyExitsException.class)
    public ResponseEntity<String> cityAlreadyException(CityAlreadyExitsException cityAlreadyExitsException) {
        return new ResponseEntity<>(cityAlreadyExitsException.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
