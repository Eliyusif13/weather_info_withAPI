package com.sadiqov.weatherforecast.config;

import com.sadiqov.weatherforecast.service.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WeatherSecurity {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/auth/register","/api/city")
                .permitAll()
                .antMatchers("/api/weather/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and().build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, CustomAuthenticationProvider provider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(provider)
                .build();
    }
}