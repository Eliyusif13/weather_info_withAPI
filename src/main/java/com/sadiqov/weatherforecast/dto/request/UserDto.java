package com.sadiqov.weatherforecast.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserDto {

    String username;
    String password;
    String role;
    @Column(unique = true)
    String Code;
    Integer loginAttempts ;

}
