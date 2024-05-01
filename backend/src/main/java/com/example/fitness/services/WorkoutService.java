package com.example.fitness.services;

import com.example.fitness.components.Workout;
import com.example.fitness.repositories.WorkoutRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    @Transactional
    public Workout addWorkout(Long trainerID, String workoutTitle, String workoutType, String targetAudience,
                              Integer workoutCount, Integer workoutEstimatedTime, String workoutDescription,
                              String equipments, Double calorieBurnPerUnitTime, Integer intensityLevel) {
        // Check if workout title already exists
        Optional<Workout> existingWorkout = workoutRepository.findWorkoutByTitle(workoutTitle);
        if (existingWorkout.isPresent()) {
            throw new IllegalStateException("A workout with that title already exists.");
        }

        // Create a new Workout object
        Workout workout = new Workout(trainerID, workoutTitle, workoutType, targetAudience, workoutCount,
                workoutEstimatedTime, workoutDescription, equipments, calorieBurnPerUnitTime, intensityLevel);

        // Persist the workout using raw SQL
        String insertQuery = "INSERT INTO workout(trainerID, workout_title, workout_type, target_audience, " +
                "workout_count, workout_estimated_time, workout_description, equipments, calorie_burn_per_unit_time, " +
                "intensity_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        entityManager.createNativeQuery(insertQuery)
                .setParameter(1, trainerID)
                .setParameter(2, workoutTitle)
                .setParameter(3, workoutType)
                .setParameter(4, targetAudience)
                .setParameter(5, workoutCount)
                .setParameter(6, workoutEstimatedTime)
                .setParameter(7, workoutDescription)
                .setParameter(8, equipments)
                .setParameter(9, calorieBurnPerUnitTime)
                .setParameter(10, intensityLevel)
                .executeUpdate();

        // Return the workout object
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
    public void updateWorkout(Long workoutID, Long trainerID, String workoutTitle, String workoutType, String targetAudience, int workoutCount,
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

        if (workoutCount > 0 && !Objects.equals(workoutCount, workout.getWorkoutCount())) {
            workout.setWorkoutCount(workoutCount);
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

