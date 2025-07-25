package com.sadiqov.weatherforecast.repository;

import com.sadiqov.weatherforecast.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LoginUser, Long> {

    Optional<LoginUser> findByUsername(String username);

    boolean existsByUsername(String username);

}