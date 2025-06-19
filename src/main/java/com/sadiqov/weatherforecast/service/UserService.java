package com.sadiqov.weatherforecast.service;

import com.sadiqov.weatherforecast.dto.UserDto;
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

    if (userRepository.existsByUsername(userDto.getUsername())){
        throw UserCodeAlreadyExistsException.builder()
                .responseDTO(CommonResponse.builder()
                        .status(Status.builder()
                                .statusCode(StatusCode.USER_NOT_EXITS)
                                .message("Registration Failed, check to log in by changing the COD or UserName")
                                .build())
                        .build())
                .build()
                ;

    }
        LoginUser user = new LoginUser();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }
}
