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

    public Workout addWorkout( Long trainerID, Workout workout) {
        logger.debug("Adding workout for trainer ID: {}", trainerID);
        logger.debug("Received workout details: {}", workout);

        /*if (!trainerRepository.existsById(trainerID)) {
            throw new TrainerNotFoundException("Trainer with ID " + trainerID + " not found");
        }*/

        // Assuming you have a workout entity and repository
        entityManager.createNativeQuery("INSERT INTO workout (trainer_id, workout_title, workout_type, target_audience, workout_estimated_time, workout_description, equipments, calorie_burn_per_unit_time, intensity_level) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
        .setParameter(1, trainerID)
        .setParameter(2, workout.getWorkoutTitle())
        .setParameter(3, workout.getWorkoutType())
        .setParameter(4, workout.getTargetAudience())
        .setParameter(5, workout.getWorkoutEstimatedTime())
        .setParameter(6, workout.getWorkoutDescription())
        .setParameter(7, workout.getEquipments())
        .setParameter(8, workout.getCalorieBurnPerUnitTime())
        .setParameter(9, workout.getIntensityLevel())
        .executeUpdate();

        logger.debug("Workout added successfully for trainer ID: {}", trainerID);
        return workout;
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
                              int workoutEstimatedTime, String workoutDescription, String equipments, double calorieBurnPerUnitTime, int intensityLevel){

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
        return workoutRepository.findAll();
    }

    public Optional<Workout> getWorkoutsByTrainerId(Long trainerId) {
        return workoutRepository.findWorkoutByTrainer(trainerId);
    }

    @Transactional
    public List<Workout> getWorkoutsByEstimatedTime(int estimatedTime) {
        return workoutRepository.findWorkoutsByEstimatedTime(estimatedTime);
    }

    @Transactional
    public List<Workout> getWorkoutsByCaloriesBurnt(double caloriesBurnt) {
        return workoutRepository.findWorkoutsByCaloriesBurnt(caloriesBurnt);
    }

    @Transactional
    public List<Workout> getWorkoutsByEquipment(String equipment) {
        return workoutRepository.findWorkoutsByEquipment(equipment);
    }
}



