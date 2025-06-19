package com.sadiqov.weatherforecast.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UpdateUserRequest {
    String currentUsername;
    String currentPassword;

    String newUsername;
    String newPassword;
}

