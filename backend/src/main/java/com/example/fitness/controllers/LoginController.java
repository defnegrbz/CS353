package com.example.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.services.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Object userLogin(@RequestParam (required = true) String username, @RequestParam (required = true) String password) {
        return loginService.userLogin(username, password);
    }
}
