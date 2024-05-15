package com.example.fitness.repositories;

import com.example.fitness.components.WorkoutLog;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.workout_log_id = ?1", nativeQuery = true)
    Optional<WorkoutLog> findWorkoutLogById(Long id);

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.workout_log_date = ?1", nativeQuery = true)
    List<WorkoutLog> findWorkoutLogsByDate(String date);

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.member_id = ?1", nativeQuery = true)
    List<WorkoutLog> findWorkoutLogsByMember(Long memberId);

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.workout_log_duration <= ?1", nativeQuery = true)
    List<WorkoutLog> findWorkoutLogsByDurationLessThan(String duration);

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.workout_log_status = ?1", nativeQuery = true)
    List<WorkoutLog> findWorkoutLogsByStatus(String status);

    @Query(value = "SELECT * FROM workoutlog wl WHERE wl.workout_log_totalcaloriesburnt <= ?1", nativeQuery = true)
    List<WorkoutLog> findWorkoutLogsByTotalCaloriesBurntLessThan(String caloriesBurnt);

    @Query(value = "SELECT * FROM workoutlog", nativeQuery = true)
    List<WorkoutLog> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM workoutlog wl WHERE wl.workout_log_id = ?1", nativeQuery = true)
    void deleteById(Long workoutLogId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO workoutlog (member_id, workout_log_date, workout_log_duration, workout_log_status, workout_log_totalcaloriesburnt) " +
                   "VALUES (:memberId, :date, :duration, :status, :caloriesBurnt)",
                   nativeQuery = true)
    void addWorkoutLog(@Param("memberId") Long memberId, @Param("date") String date, @Param("duration") String duration,
                    @Param("status") String status, @Param("caloriesBurnt") String caloriesBurnt);



    
    

}









