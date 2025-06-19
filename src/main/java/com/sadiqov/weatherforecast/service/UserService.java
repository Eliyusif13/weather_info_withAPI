package com.sadiqov.weatherforecast.service;

import com.sadiqov.weatherforecast.dto.UserDto;
import com.sadiqov.weatherforecast.dto.request.UpdateUserRequest;
import com.sadiqov.weatherforecast.dto.response.CommonResponse;
import com.sadiqov.weatherforecast.dto.response.Status;
import com.sadiqov.weatherforecast.dto.response.StatusCode;
import com.sadiqov.weatherforecast.entity.LoginUser;
import com.sadiqov.weatherforecast.excebtion.UserCodeAlreadyExistsException;
import com.sadiqov.weatherforecast.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginUser register(UserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw exceptionMessage(StatusCode.USER_EXITS,
                    "Registration Failed, check to log in by changing the COD or UserName");

        }
        LoginUser user = new LoginUser();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }


    public void deleteByUsernameAndPassword(String username, String rawPassword) {
        LoginUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> exceptionMessage(StatusCode.USER_NOT_EXITS,"User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw exceptionMessage(StatusCode.INCORRECT_PASSWORD,"Password not found");
        }
        userRepository.delete(user);
    }


    public void updateUser(UpdateUserRequest updateUserRequest) {
        LoginUser user = userRepository.findByUsername(updateUserRequest.getCurrentUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(updateUserRequest.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid current password");
        }

        if (updateUserRequest.getNewUsername() != null && !updateUserRequest.getNewUsername().isBlank()) {
            user.setUsername(updateUserRequest.getNewUsername());
        }

        if (updateUserRequest.getNewPassword() != null && !updateUserRequest.getNewPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(updateUserRequest.getNewPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
    }


    private  UserCodeAlreadyExistsException exceptionMessage(StatusCode statusCode, String message) {
        return UserCodeAlreadyExistsException.builder().
                responseDTO(CommonResponse.builder().
                        status(Status.builder().statusCode(statusCode).
                                message(message).
                                build()).build()).build();
    }
}
