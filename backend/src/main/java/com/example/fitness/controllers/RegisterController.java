package com.example.fitness.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;
import com.example.fitness.requests.RegisterRequest;
import com.example.fitness.requests.RegisterTrainerRequest;
import com.example.fitness.services.UserService;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/member")
    public Member registerMember(@RequestBody RegisterRequest request) {
        return userService.registerMember(request);
    }

    @PostMapping("/trainer")
    public Trainer registerTrainer(@RequestBody RegisterTrainerRequest request) {
        return userService.registerTrainer(request);
    }
}

