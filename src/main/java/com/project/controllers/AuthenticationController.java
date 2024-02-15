package com.project.controllers;

import com.project.payloads.LoginResponseDTO;
import com.project.payloads.RegistrationDTO;
import com.project.models.User;
import com.project.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body)
    {
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getRole());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body)
    {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
