package com.example.fitness.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.User;
import com.example.fitness.repositories.UserRepository;
import com.example.fitness.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
     
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}") 
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById((userId));
    }

    // Register a new user
    //@PostMapping("/register/member")
    //@PostMapping("/register/trainer")
    //Member ve trainer registerleri PostMapping kısmi bu şekilde olmalı react için
    
    // Login
    //@PostMapping("/login/member-login")
    //@PostMapping("/login/trainer-login")
    //Member ve trainer loginleri PostMapping kısmi bu şekilde olmalı react için
    

}
