package com.example.fitness.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController{
     
    private UserService userService;

    public TrainerController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public List<Trainer> getAllTrainers(){
        return userService.getAllTrainers();
    }

    @GetMapping("/{trainerID}/busyDates")
    public ResponseEntity<List<LocalDate>> getBusyDates(@PathVariable Long trainerID) {
        List<LocalDate> busyDates = userService.getBusyDates(trainerID);
        return ResponseEntity.ok(busyDates);
    }

    @PostMapping
    public void createTrainer(@RequestBody Trainer newTrainer){
        //userService.saveOneTrainer(newTrainer);
    }

    @GetMapping("/oneTrainer/{trainerId}") 
    public Trainer getOneMember(@PathVariable Long trainerId){
        return userService.getOneTrainer(trainerId);
    }

    @PutMapping("/update/{trainerId}")
    public void updateOneTrainer(@PathVariable Long trainerId, @RequestBody Trainer newTrainer){
        //userService.updateOneTrainer(trainerId, newTrainer);
    }

    @DeleteMapping("/delete/{trainerId}")
    public void deleteOneTrainer(@PathVariable Long trainerId){
        userService.deleteByIdTrainer((trainerId));
    }

}
