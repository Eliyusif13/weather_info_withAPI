package com.sadiqov.weatherforecast.excebtion;

import com.sadiqov.weatherforecast.dto.response.CommonResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoSuchUserExits extends RuntimeException {
    CommonResponse<?> responseDTO;


    public NoSuchUserExits(CommonResponse<?> responseDTO) {
        super(responseDTO.getStatus().getMessage());
        this.responseDTO = responseDTO;

    }
}