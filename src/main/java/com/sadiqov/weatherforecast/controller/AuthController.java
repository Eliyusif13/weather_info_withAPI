package com.sadiqov.weatherforecast.controller;

import com.sadiqov.weatherforecast.dto.request.UserDto;
import com.sadiqov.weatherforecast.dto.request.*;
import com.sadiqov.weatherforecast.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.ok("User register successfully...");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);

        // Null yoxlaması əlavə et
        if (userDto.getLoginAttempts() != null && userDto.getLoginAttempts().equals(3)) {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", "Too many failed attempts. Forgot password?");
            message.put("redirect", "/auth/forgot-password");
            return ResponseEntity.ok(message);
        }

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }


    @PostMapping("/auth/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        userService.resetPassword(request.getUsername(), request.getNewPassword());
        return ResponseEntity.ok("Password successfully updated.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody DeleteRequestDto request) {
        userService.deleteByUsernameAndPassword(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
        return ResponseEntity.ok("User updated successfully");
    }
}