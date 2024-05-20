package com.example.fitness.repositories;

import com.example.fitness.components.Workout;
import com.example.fitness.components.WorkoutCatalog;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query(value = "SELECT * FROM workout w WHERE w.workoutID = ?1", nativeQuery = true)
    Optional<Workout> findWorkoutByID(Long id);

    @Query(value = "SELECT * FROM workout w WHERE w.workoutID = ?1", nativeQuery = true)
    Optional<Workout> findWorkoutByTitle(String title);

    @Query(value = "SELECT * FROM workout w WHERE w.trainerID = ?1", nativeQuery = true)
    Optional<Workout> findWorkoutByTrainer(Long tid);

    @Query(value = "SELECT * FROM workout w WHERE w.target_audience = :targetAudience", nativeQuery = true)
    List<Workout> findWorkoutsByTargetAudience(@Param("targetAudience") String targetAudience);

    @Query(value = "SELECT * FROM workout w WHERE w.workout_type = :workout_type", nativeQuery = true)
    List<Workout> findWorkoutsByType(@Param("workout_type") String targetAudience);

    @Query("SELECT w FROM Workout w WHERE w.calorieBurnPerUnitTime >= :minCalories AND w.calorieBurnPerUnitTime <= :maxCalories")
    List<Workout> findWorkoutsByCaloriesBurnt(@Param("minCalories") double minCalories, @Param("maxCalories") double maxCalories);

    @Query("SELECT w FROM Workout w WHERE w.workoutEstimatedTime <= :estimatedTime")
    List<Workout> findWorkoutsByEstimatedTime(@Param("estimatedTime") int estimatedTime);

    @Query(value = "SELECT * FROM workout w WHERE w.intensity_level = :intensity", nativeQuery = true)
    List<Workout> findWorkoutsByIntensity(@Param("intensity") int intensity);

    @Query(value = "SELECT * FROM workout w WHERE w.workout_title LIKE %:title%", nativeQuery = true)
    List<Workout> findByWorkoutTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT w FROM Workout w WHERE w.equipments LIKE %:equipment%")
    List<Workout> findWorkoutsByEquipment(@Param("equipment") String equipment);

    @Query(value= "SELECT w FROM workout w ORDER BY w.intensityLevel :order", nativeQuery=true)
    List<Workout> sortWorkoutsByIntensity(@Param("order") String order);

    @Query(value = "SELECT w FROM workout w ORDER BY w.workoutEstimatedTime :order", nativeQuery = true)
    List<Workout> sortWorkoutsByEstimatedTime(@Param("order") String order);

    @Query(value = "SELECT w FROM workout w ORDER BY w.calorieBurnPerUnitTime :order", nativeQuery = true)
    List<Workout> sortWorkoutsByCaloriesBurnt(@Param("order") String order);

    @Query(value = "SELECT * FROM workout w", nativeQuery = true)
    List<Workout> findAll();

    @Query(value = "SELECT * FROM WorkoutCatalog w", nativeQuery = true)
    List<WorkoutCatalog> findAllWorkouts();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM workout w WHERE w.workoutID = ?1", nativeQuery = true)
    void deleteById(Long workoutID);

    

   
}


