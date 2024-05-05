package com.example.fitness.controllers;

import com.example.fitness.components.User;
import com.example.fitness.services.HealthDataService;

import java.util.List;
import java.util.Optional;

import com.example.fitness.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.fitness.components.HealthData;
import com.example.fitness.repositories.HealthDataRepository;

@RestController
@RequestMapping("/healthdatas")

public class HealthDataController {
    private UserService userService;
    private HealthDataService healthDataService;

    public HealthDataController(UserService userService, HealthDataService healthDataService){
        this.userService = userService;
        this.healthDataService = healthDataService;
    }

    @PostMapping("/{userId}/create")
    public HealthData createHealthData(@PathVariable Long userId, @RequestBody HealthData newHealthData){
        User user = userService.getOneUser(userId);
        newHealthData.setUser(user);
        return healthDataService.saveOneHealthData(newHealthData);
    }

    @GetMapping("/{userId}") 
    public HealthData getOneHealthData(@PathVariable Long userId){
        return healthDataService.getOneHealthData(userId);
    }

    @PutMapping("/{userId}")
    public HealthData updateOneHealthData(@PathVariable Long userId, @RequestBody HealthData newHealthData){
        return healthDataService.updateOneHealthData(userId, newHealthData);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneHealthData(@PathVariable Long userId){
        healthDataService.deleteById((userId));
    }

}
