package com.example.fitness.controllers;

import com.example.fitness.components.Workout;
import com.example.fitness.components.WorkoutLog;
import com.example.fitness.requests.WorkoutLogRequest;
import com.example.fitness.services.WorkoutLogService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/workoutlogs")
public class WorkoutLogController {

    private static final Logger logger = LogManager.getLogger(WorkoutLogController.class);

    private final WorkoutLogService workoutLogService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager; // Initialize the entityManager object

   

    @Autowired
    public WorkoutLogController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutLog>> getAllWorkoutLogs() {
        List<WorkoutLog> workoutLogs = workoutLogService.findAllWorkoutLogs();
        return ResponseEntity.ok().body(workoutLogs);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<WorkoutLog>> getWorkoutLogsByMember(@PathVariable Long memberId) {
        List<WorkoutLog> workoutLogs = workoutLogService.findWorkoutLogsByMember(memberId);
        return ResponseEntity.ok().body(workoutLogs);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<WorkoutLog>> getWorkoutLogsByDate(@PathVariable String date) {
        List<WorkoutLog> workoutLogs = workoutLogService.findWorkoutLogsByDate(date);
        return ResponseEntity.ok().body(workoutLogs);
    }

    @Transactional
    @PostMapping("/{memberId}/createworkoutlog")
    public WorkoutLog addWorkoutLog(Long memberId, WorkoutLog workoutLog) {
        logger.debug("Adding workout log for member ID: {}", memberId);
        logger.debug("Received workout log details: {}", workoutLog);

        try {
            entityManager.createNativeQuery("INSERT INTO workoutlog (member_id, workout_log_date, workout_log_duration, workout_log_status, workout_log_totalcaloriesburnt, workout_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, memberId)
                .setParameter(2, workoutLog.getWorkoutLogDate())
                .setParameter(3, workoutLog.getWorkoutLogDuration())
                .setParameter(4, workoutLog.getWorkoutLogStatus())
                .setParameter(5, workoutLog.getWorkoutLogTotalCaloriesBurnt())
                .setParameter(6, workoutLog.getWorkoutId())
                .executeUpdate();

            logger.debug("Workout log added successfully for member ID: {}", memberId);
        } catch (Exception e) {
            logger.error("Error adding workout log: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add workout log", e);
        }

        return workoutLog;
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutLog(@PathVariable Long id) {
        //workoutLogService.deleteWorkoutLog(id);
        return ResponseEntity.noContent().build();
    }

    // Other endpoints for workout log management, like updating logs, can be added here.
}
