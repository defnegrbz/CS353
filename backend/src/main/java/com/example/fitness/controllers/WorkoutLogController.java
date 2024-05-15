package com.example.fitness.controllers;

import com.example.fitness.components.WorkoutLog;
import com.example.fitness.requests.WorkoutLogRequest;
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

    @PostMapping
    public ResponseEntity<WorkoutLog> addWorkoutLog(@RequestBody WorkoutLogRequest workoutLogRequest) {
        WorkoutLog newWorkoutLog = workoutLogService.addWorkoutLog(
            workoutLogRequest.getMemberId(),
            workoutLogRequest.getDate(),
            workoutLogRequest.getDuration(),
            workoutLogRequest.getStatus(),
            workoutLogRequest.getCaloriesBurnt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkoutLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutLog(@PathVariable Long id) {
        //workoutLogService.deleteWorkoutLog(id);
        return ResponseEntity.noContent().build();
    }

    // Other endpoints for workout log management, like updating logs, can be added here.
}
