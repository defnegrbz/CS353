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



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutLog(@PathVariable Long id) {
        //workoutLogService.deleteWorkoutLog(id);
        return ResponseEntity.noContent().build();
    }

}
