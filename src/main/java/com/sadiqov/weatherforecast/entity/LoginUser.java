package com.sadiqov.weatherforecast.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String username;
    @Column(unique = true)
     String password;
     String role;

}