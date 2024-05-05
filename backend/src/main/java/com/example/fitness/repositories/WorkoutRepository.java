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

    

   
}


