package com.example.fitness.controllers;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.Workout;
import com.example.fitness.repositories.WorkoutRepository;
import com.example.fitness.requests.WorkoutRequest;
import com.example.fitness.services.WorkoutService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/workouts")

public class WorkoutController {

    private static final Logger logger = LogManager.getLogger(WorkoutController.class);

    private final WorkoutService workoutService;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutController(WorkoutService workoutService, WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getWorkouts(){
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/targetAudience={targetAudience}")
    public List<Workout> getWorkoutsByTargetAudience(@PathVariable String targetAudience) {
        return workoutService.getWorkoutsByTargetAudience(targetAudience);
    }

    @GetMapping("/workout_type={workout_type}")
    public List<Workout> getWorkoutsByType(@PathVariable String workout_type) {
        return workoutService.getWorkoutsByType(workout_type);
    }

    @GetMapping("/minCalories={minCalories}&maxCalories={maxCalories}")
    public List<Workout> getWorkoutsByCalorieBurn(@PathVariable double minCalories, @PathVariable double maxCalories) {
        return workoutService.getWorkoutsByCaloriesBurnt(minCalories, maxCalories);
    }

    @GetMapping("/intensity={intensity}")
    public List<Workout> getWorkoutsByIntensity(@PathVariable int intensity) {
        return workoutService.getWorkoutsByIntensity(intensity);
    }

    @GetMapping("/intensitysort/{order}")
    public List<Workout> getWorkoutsByIntensitySort(@PathVariable String order) {
        return workoutService.getWorkoutsByIntensitySort(order);
    }

    @GetMapping("/caloriesort/{order}")
    public List<Workout> getWorkoutsByCalorieSort(@PathVariable String order) {
        return workoutService.getWorkoutsByCalorieSort(order);
    }

    @GetMapping("/timesort/{order}")
    public List<Workout> getWorkoutsByTimeSort(@PathVariable String order) {
        return workoutService.getWorkoutsByTimeSort(order);
    }

    @GetMapping("/search")
    public List<Workout> searchWorkoutsByTitle(@RequestParam String title) {
        return workoutService.searchWorkoutsByTitle(title);
    }
    
    
    @PostMapping("/{trainerID}/createWorkout")
    public Workout addWorkout(@PathVariable Long trainerID, @RequestBody Workout workout) {
        logger.debug("Received request to create workout for trainer ID: {}", trainerID);
        logger.debug("Received workout details: {}", workout);

        Workout createdWorkout = workoutService.addWorkout(trainerID, workout);
        
        logger.debug("Workout created successfully: {}", createdWorkout);
        return createdWorkout;
    }

    @DeleteMapping(path="{workoutID}")
    public Workout deleteWorkout(@PathVariable("workoutID") Long workoutID){
        workoutService.deleteWorkout(workoutID);
        Workout wo;
        return wo = workoutRepository.findWorkoutByID(workoutID).orElseThrow(() -> new IllegalStateException("A workout with that ID does not exist."));
    }

    @PutMapping(path="{workoutID}")
    public Workout updateWorkout(@PathVariable("workoutID") Long workoutID, @RequestBody Workout workout) {
        workoutService.updateWorkout(workoutID, workout.getTrainerID(), workout.getWorkoutTitle(), workout.getWorkoutType(), workout.getTargetAudience(), workout.getWorkoutEstimatedTime(), workout.getWorkoutDescription(), workout.getEquipments(), workout.getCalorieBurnPerUnitTime(), workout.getIntensityLevel());
        return workout;
    }
}
