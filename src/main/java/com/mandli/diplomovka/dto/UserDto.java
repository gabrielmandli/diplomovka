package com.mandli.diplomovka.dto;

import jakarta.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mandli.diplomovka.entity.User;
import com.mandli.diplomovka.validator.PasswordMatches;

import lombok.Data;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank

    private String password;

    @NotBlank
    private String confirm;

    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phone);
    }

}
