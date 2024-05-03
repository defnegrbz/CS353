package com.example.fitness.repositories;

import com.example.fitness.components.Workout;

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

    @Query("SELECT w FROM Workout w WHERE w.workoutEstimatedTime <= :estimatedTime")
    List<Workout> findWorkoutsByEstimatedTime(@Param("estimatedTime") int estimatedTime);

    @Query("SELECT w FROM Workout w WHERE w.calorieBurnPerUnitTime <= :caloriesBurnt")
    List<Workout> findWorkoutsByCaloriesBurnt(@Param("caloriesBurnt") double caloriesBurnt);

    @Query("SELECT w FROM Workout w WHERE w.equipments LIKE %:equipment%")
    List<Workout> findWorkoutsByEquipment(@Param("equipment") String equipment);

    @Query(value = "SELECT * FROM workout w", nativeQuery = true)
    List<Workout> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM workout w WHERE w.workoutID = ?1", nativeQuery = true)
    void deleteById(Long workoutID);

    

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO workout (trainerid, workout_title, workout_type, target_audience, workout_count, workout_estimated_time, workout_description, equipments, calorie_burn_per_unit_time, intensity_level) " +
                   "VALUES (:trainerid, :workout_title, :workout_type, :target_audience, :workout_count, :workout_estimated_time, :workout_description, :equipments, :calorie_burn_per_unit_time, :intensity_level)",
                   nativeQuery = true)
    void addWorkout(@Param("trainerid") Long trainerid, @Param("workout_title") String workout_title, @Param("workout_type") String workout_type,
                    @Param("target_audience") String target_audience, @Param("workout_count") Integer workout_count,
                    @Param("workout_estimated_time") Integer workout_estimated_time, @Param("workout_description") String workout_description,
                    @Param("equipments") String equipments, @Param("calorie_burn_per_unit_time") Double calorie_burn_per_unit_time,
                    @Param("intensity_level") Integer intensity_level);
}


