package com.example.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.User;
import com.example.fitness.requests.LoginRequest;
import com.example.fitness.services.LoginService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
     private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Long> userLogin(@RequestBody LoginRequest request) {
        logger.debug("UsernameController: {}", request.getUsername());
        logger.debug("PasswordController: {}", request.getPassword());
        return loginService.userLogin(request);
    }
}
