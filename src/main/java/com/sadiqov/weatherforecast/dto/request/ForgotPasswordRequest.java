package com.sadiqov.weatherforecast.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Data
public class ForgotPasswordRequest {
   String username;
   String newPassword;
}
