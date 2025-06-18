package com.sadiqov.weatherforecast.excebtion;

import com.sadiqov.weatherforecast.dto.response.CommonResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCodeAlreadyExistsException extends RuntimeException {
    CommonResponse<?> responseDTO;


    public UserCodeAlreadyExistsException(CommonResponse<?> responseDTO) {
        super(responseDTO.getStatus().getMessage()); // Exception message olaraq status mesajını da ver
        this.responseDTO = responseDTO;
    }
}
