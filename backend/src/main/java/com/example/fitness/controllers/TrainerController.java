package com.example.fitness.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
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
@CrossOrigin
public class TrainerController{
     
    private UserService userService;

    public TrainerController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        try {
            List<Trainer> trainers = userService.getAllTrainers();
            return new ResponseEntity<>(trainers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{trainerID}/busyDates")
    public ResponseEntity<List<LocalDate>> getBusyDates(@PathVariable Long trainerID) {
        List<LocalDate> busyDates = userService.getBusyDates(trainerID);
        return ResponseEntity.ok(busyDates);
    }

    @PostMapping("/{trainerID}/add-busy-dates`")
    public ResponseEntity<String> addBusyDate(@PathVariable Long trainerID, @RequestBody LocalDate date) {
        String email = userService.addBusyDate(trainerID, date);
        if (email != null) {
            return ResponseEntity.ok("Date successfully added. Trainer's email: " + email);
        } else {
            return ResponseEntity.status(404).body("Trainer not found");
        }
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


    // for voting system
    @PutMapping("/vote/{trainerId}")
    public void voteTrainer(@PathVariable Long trainerId, @RequestBody Integer vote){
        userService.voteTrainer(trainerId, vote);
    }

}
