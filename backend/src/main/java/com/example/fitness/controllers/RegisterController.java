package com.example.fitness.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.fitness.components.Member;
import com.example.fitness.requests.RegisterRequest;
import com.example.fitness.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /* @PostMapping("/member")
    public ResponseEntity<String> registerMember(@RequestBody RegisterRequest request) {
        try {
            userService.registerMember(request);
            return ResponseEntity.ok("Member registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    } */

    @PostMapping("/member")
    public Member registerMember(@RequestBody RegisterRequest request) {
        return userService.registerMember(request);
    }
}

