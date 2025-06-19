package com.sadiqov.weatherforecast.controller;

import com.sadiqov.weatherforecast.dto.UserDto;
import com.sadiqov.weatherforecast.dto.request.DeleteRequestDto;
import com.sadiqov.weatherforecast.dto.request.UpdateUserRequest;
import com.sadiqov.weatherforecast.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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