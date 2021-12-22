package com.mandli.diplomovka.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mandli.diplomovka.dto.UserDto;
import com.mandli.diplomovka.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getRegisterForm(@ModelAttribute("user") UserDto userDto)
    {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userRepository.save(userDto.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
