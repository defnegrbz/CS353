package com.example.fitness.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.Trainer;
import com.example.fitness.services.UserService;

@RestController
@RequestMapping("/trainers")
public class TrainerController{
     
    private UserService userService;

    public TrainerController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<Trainer> getAllTrainers(){
        return userService.getAllTrainers();
    }

    @PostMapping
    public void createTrainer(@RequestBody Trainer newTrainer){
        //userService.saveOneTrainer(newTrainer);
    }

    @GetMapping("/{trainerId}") 
    public Trainer getOneMember(@PathVariable Long trainerId){
        return userService.getOneTrainer(trainerId);
    }

    @PutMapping("/{trainerId}")
    public void updateOneTrainer(@PathVariable Long trainerId, @RequestBody Trainer newTrainer){
        //userService.updateOneTrainer(trainerId, newTrainer);
    }

    @DeleteMapping("/{trainerId}")
    public void deleteOneTrainer(@PathVariable Long trainerId){
        userService.deleteByIdTrainer((trainerId));
    }

}
