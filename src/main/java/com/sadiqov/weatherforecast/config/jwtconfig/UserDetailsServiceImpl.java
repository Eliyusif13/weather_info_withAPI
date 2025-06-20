package com.sadiqov.weatherforecast.config.jwtconfig;

import com.sadiqov.weatherforecast.dto.response.CommonResponse;
import com.sadiqov.weatherforecast.dto.response.Status;
import com.sadiqov.weatherforecast.dto.response.StatusCode;
import com.sadiqov.weatherforecast.entity.LoginUser;
import com.sadiqov.weatherforecast.excebtion.NoSuchUserExits;
import com.sadiqov.weatherforecast.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginUser> byPin = userRepository.findByUsername(username);
        if (byPin.isPresent()) {
            return new UserDetailsImpl(byPin.get());
        } else {
            throw NoSuchUserExits.builder().responseDTO(CommonResponse.builder().
                    status(Status.builder().statusCode(StatusCode.USER_NOT_EXITS).
                            message("There is no user with username : " + username)
                            .build()).build()).build();
        }
    }
}
