package com.example.fitness.controllers;

import com.example.fitness.components.WorkoutLog;
import com.example.fitness.services.WorkoutLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/workoutlogs")
public class WorkoutLogController {

    private final WorkoutLogService workoutLogService;

    @Autowired
    public WorkoutLogController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<WorkoutLog>> getWorkoutLogsByMember(@PathVariable Long memberId) {
        List<WorkoutLog> workoutLogs = workoutLogService.findWorkoutLogsByMember(memberId);
        return ResponseEntity.ok().body(workoutLogs);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<WorkoutLog>> getWorkoutLogsByDate(@PathVariable String date) {
        List<WorkoutLog> workoutLogs = workoutLogService.findWorkoutLogsByDate(date);
        return ResponseEntity.ok().body(workoutLogs);
    }

    @PostMapping
    public ResponseEntity<WorkoutLog> addWorkoutLog(@RequestBody Long memberId, @RequestBody String date, @RequestBody String duration, @RequestBody String status, @RequestBody String caloriesBurnt) {
        WorkoutLog newWorkoutLog = workoutLogService.addWorkoutLog(memberId, date, duration, status, caloriesBurnt);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkoutLog);
    }

    // Other endpoints for workout log management, like adding a new log, updating, or deleting by ID
}
