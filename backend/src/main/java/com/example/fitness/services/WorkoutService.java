package com.example.fitness.services;

import com.example.fitness.components.Workout;
import com.example.fitness.repositories.WorkoutRepository;
import com.example.fitness.requests.WorkoutRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class WorkoutService {
    private static final Logger logger = LogManager.getLogger(WorkoutService.class);


    private final WorkoutRepository workoutRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    @Transactional
public Workout addWorkout(Long trainerID, Workout workout) {
    logger.debug("Adding workout for trainer ID: {}", trainerID);
    logger.debug("Received workout details: {}", workout);

    if (workout == null) {
        logger.error("Workout details are null");
        throw new IllegalArgumentException("Workout details must not be null");
    }

    // Check for required fields
    if (workout.getCalorieBurnPerUnitTime() == null || 
        workout.getWorkoutTitle() == null || 
        workout.getWorkoutType() == null || 
        workout.getTargetAudience() == null || 
        workout.getWorkoutEstimatedTime() == null || 
        workout.getWorkoutDescription() == null) {
        
        logger.error("Critical workout information is missing");
        throw new IllegalArgumentException("All critical workout information must be provided");
    }

    try {
        // Execute native SQL query to insert workout data
        entityManager.createNativeQuery("INSERT INTO workout (trainerid, workout_title, workout_type, target_audience, workout_estimated_time, workout_description, calorie_burn_per_unit_time, intensity_level, equipments) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .setParameter(1, trainerID)
            .setParameter(2, workout.getWorkoutTitle())
            .setParameter(3, workout.getWorkoutType())
            .setParameter(4, workout.getTargetAudience())
            .setParameter(5, workout.getWorkoutEstimatedTime())
            .setParameter(6, workout.getWorkoutDescription())
            .setParameter(7, workout.getCalorieBurnPerUnitTime())
            .setParameter(8, workout.getIntensityLevel())
            .setParameter(9, workout.getEquipments())
            
            .executeUpdate();

        logger.debug("Workout added successfully for trainer ID: {}", trainerID);
    } catch (Exception e) {
        logger.error("Error adding workout: {}", e.getMessage(), e);
        throw new RuntimeException("Failed to add workout", e);
    }

    return workout; // Consider refreshing the workout entity to ensure it contains the generated ID and any other defaults set by the database.
}

    
    
    @Transactional
    public void deleteWorkout(Long workoutID){
        Optional<Workout> workoutOptionalId = workoutRepository.findWorkoutByID(workoutID);
        if(!workoutOptionalId.isPresent()){
            throw new IllegalStateException("A workout with that ID does not exist.");
        }
        workoutRepository.deleteById(workoutID);
    }

    @Transactional
    public void updateWorkout(Long workoutID, Long trainerID, String workoutTitle, String workoutType, String targetAudience,
                              int workoutEstimatedTime, String workoutDescription, String equipments, int calorieBurnPerUnitTime, int intensityLevel){

        Optional<Workout> workoutOptional = workoutRepository.findWorkoutByID(workoutID);
        if (!workoutOptional.isPresent()) {
            throw new IllegalStateException("A workout with that ID does not exist.");
        }

        Workout workout = workoutOptional.get();
        // trainerID ile olan bir trainer var mı check

        if (workoutTitle!=null && !Objects.equals(workoutTitle, workout.getWorkoutTitle())) {
            workout.setWorkoutTitle(workoutTitle);
        }

        if (workoutType!=null && !Objects.equals(workoutType, workout.getWorkoutType())) {
            workout.setWorkoutType(workoutType);
        }

        if (targetAudience!=null && !Objects.equals(targetAudience, workout.getTargetAudience())) {
            workout.setTargetAudience(targetAudience);
        }

        if (workoutEstimatedTime > 0 && !Objects.equals(workoutEstimatedTime, workout.getWorkoutEstimatedTime())) {
            workout.setWorkoutEstimatedTime(workoutEstimatedTime);
        }

        if (workoutDescription!=null && !Objects.equals(workoutDescription, workout.getWorkoutDescription())) {
            workout.setWorkoutDescription(workoutDescription);
        }

        if (equipments!=null && !Objects.equals(equipments, workout.getEquipments())) {
            workout.setEquipments(equipments);
        }

        if (calorieBurnPerUnitTime > 0 && !Objects.equals(calorieBurnPerUnitTime, workout.getCalorieBurnPerUnitTime())) {
            workout.setCalorieBurnPerUnitTime(calorieBurnPerUnitTime);
        }

        if (intensityLevel > 0 && !Objects.equals(intensityLevel, workout.getIntensityLevel())) {
            workout.setIntensityLevel(intensityLevel);
        }
    }
    

    public Workout getWorkoutById(Long workoutId) {
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        return workout.orElse(null);
    }

    public List<Workout> getAllWorkouts() {
        List<Workout> workouts = workoutRepository.findAll();
        
        // Iterate over each workout and handle null intensity level
        workouts.forEach(workout -> {
            if (workout.getIntensityLevel() == null) {
                // Handle null intensity level, e.g., provide a default value
                workout.setIntensityLevel(0); // Default intensity level
            }
        });
        return workouts;
    }

    public List<Workout> getWorkoutsByTargetAudience(String targetAudience){
        List<Workout> workouts = workoutRepository.findWorkoutsByTargetAudience(targetAudience);
        return workouts;
    }

    public List<Workout> getWorkoutsByType(String workout_type){
        List<Workout> workouts = workoutRepository.findWorkoutsByType(workout_type);
        return workouts;
    }

    @Transactional
    public List<Workout> getWorkoutsByEstimatedTime(int estimatedTime) {
        return workoutRepository.findWorkoutsByEstimatedTime(estimatedTime);
    }

    public Optional<Workout> getWorkoutsByTrainerId(Long trainerId) {
        return workoutRepository.findWorkoutByTrainer(trainerId);
    }

    @Transactional
    public List<Workout> getWorkoutsByIntensity(int intensity) {
        return workoutRepository.findWorkoutsByIntensity(intensity);
    }

    @Transactional
    public List<Workout> getWorkoutsByCaloriesBurnt(double minCalories, double maxCalories) {
        return workoutRepository.findWorkoutsByCaloriesBurnt(minCalories, maxCalories);
    }

    @Transactional
    public List<Workout> getWorkoutsByEquipment(String equipment) {
        return workoutRepository.findWorkoutsByEquipment(equipment);
    }
}



