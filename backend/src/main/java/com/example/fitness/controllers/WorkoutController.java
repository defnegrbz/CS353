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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/workouts")

public class WorkoutController {

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
    
    @PutMapping("/{trainerID}/createWorkout")
public ResponseEntity<Workout> addWorkout(@PathVariable Long trainerID, @RequestBody Map<String, Object> payload) {
    // Extracting payload values
    String workoutTitle = (String) payload.get("workout_title");
    String workoutType = (String) payload.get("workout_type");
    String targetAudience = (String) payload.get("target_audience");
    Integer workoutCount = (Integer) payload.get("workout_count");
    Integer workoutEstimatedTime = (Integer) payload.get("workout_estimated_time");
    String workoutDescription = (String) payload.get("workout_description");
    String equipments = (String) payload.get("equipments");
    Double calorieBurnPerUnitTime = (Double) payload.get("calories_burn_per_unit_time");
    Integer intensityLevel = (Integer) payload.get("intensity_level");

    // Call the service method to add the workout
    Workout workout = workoutService.addWorkout(trainerID, workoutTitle, workoutType, targetAudience, workoutCount,
            workoutEstimatedTime, workoutDescription, equipments, calorieBurnPerUnitTime, intensityLevel);

    // Return the response
    return new ResponseEntity<>(workout, HttpStatus.CREATED);
}

    @DeleteMapping(path="{workoutID}")
    public Workout deleteWorkout(@PathVariable("workoutID") Long workoutID){
        workoutService.deleteWorkout(workoutID);
        Workout wo;
        return wo = workoutRepository.findWorkoutByID(workoutID).orElseThrow(() -> new IllegalStateException("A workout with that ID does not exist."));
    }

    @PutMapping(path="{workoutID}")
    public Workout updateWorkout(@PathVariable("workoutID") Long workoutID, @RequestBody Workout workout) {
        workoutService.updateWorkout(workoutID, workout.getTrainerID(), workout.getWorkoutTitle(), workout.getWorkoutType(), workout.getTargetAudience(), workout.getWorkoutCount(), workout.getWorkoutEstimatedTime(), workout.getWorkoutDescription(), workout.getEquipments(), workout.getCalorieBurnPerUnitTime(), workout.getIntensityLevel());
        return workout;
    }
}
