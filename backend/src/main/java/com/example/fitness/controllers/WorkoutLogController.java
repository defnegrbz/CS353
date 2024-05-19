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

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/workoutlogs")
public class WorkoutLogController {

    private static final Logger logger = LogManager.getLogger(WorkoutLogController.class);

    private final WorkoutLogService workoutLogService;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

   

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
    public WorkoutLog addWorkoutLog(@PathVariable Long memberId, @RequestBody WorkoutLog workoutLog) {
        logger.debug("Adding workout log for member ID: {}", memberId);
        logger.debug("Received workout log details: {}", workoutLog);

        WorkoutLog createdWorkoutLog = workoutLogService.addWorkoutLog(memberId, workoutLog);

        logger.debug("Workout log created successfully: {}", createdWorkoutLog);

        return createdWorkoutLog;
    }


    @Transactional
    @DeleteMapping("/{workout_log_id}")
    public ResponseEntity<Void> deleteWorkoutLog(@PathVariable Long workout_log_id) {
        logger.debug("Received request to delete workout log with ID: {}", workout_log_id);
        int deletedCount = entityManager.createNativeQuery("DELETE FROM workoutlog WHERE workout_log_id = :workout_log_id")
                                        .setParameter("workout_log_id", workout_log_id)
                                        .executeUpdate();
        if (deletedCount == 0) {
            logger.warn("No workout log found with ID: {}", workout_log_id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Workout log deleted successfully with ID: {}", workout_log_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{workout_log_id}")
    @Transactional
    public ResponseEntity<WorkoutLog> updateWorkoutLog(@PathVariable Long workout_log_id, @RequestBody WorkoutLogRequest workoutLogRequest) {
        logger.debug("Received request to update workout log with ID: {}", workout_log_id);
        logger.debug("Request body: {}", workoutLogRequest);
        try {
            WorkoutLog updatedWorkoutLog = workoutLogService.updateWorkoutLog(workout_log_id, workoutLogRequest);
            if (updatedWorkoutLog == null) {
                logger.warn("No workout log found with ID: {}", workout_log_id);
                return ResponseEntity.notFound().build();
            }
            logger.info("Workout log updated successfully with ID: {}", workout_log_id);
            return ResponseEntity.ok().body(updatedWorkoutLog);
        } catch (Exception e) {
            logger.error("Error updating workout log with ID: {}: {}", workout_log_id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
