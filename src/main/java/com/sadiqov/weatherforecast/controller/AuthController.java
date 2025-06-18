package com.sadiqov.weatherforecast.controller;

import com.sadiqov.weatherforecast.dto.UserDto;
import com.sadiqov.weatherforecast.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}