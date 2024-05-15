package com.example.fitness.services;

import com.example.fitness.components.Workout;
import com.example.fitness.components.WorkoutLog;
import com.example.fitness.repositories.WorkoutLogRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutLogService {

    private final WorkoutLogRepository workoutLogRepository;

    @PersistenceContext
    private EntityManager entityManager;
    

    @Autowired
    public WorkoutLogService(WorkoutLogRepository workoutLogRepository) {
        this.workoutLogRepository = workoutLogRepository;
    }

    public Optional<WorkoutLog> findWorkoutLogById(Long id) {
        return workoutLogRepository.findWorkoutLogById(id);
    }

    public List<WorkoutLog> findWorkoutLogsByDate(String date) {
        return workoutLogRepository.findWorkoutLogsByDate(date);
    }

    public List<WorkoutLog> findWorkoutLogsByMember(Long memberId) {
        return workoutLogRepository.findWorkoutLogsByMember(memberId);
    }

    public List<WorkoutLog> findWorkoutLogsByDurationLessThan(String duration) {
        return workoutLogRepository.findWorkoutLogsByDurationLessThan(duration);
    }

    public List<WorkoutLog> findWorkoutLogsByStatus(String status) {
        return workoutLogRepository.findWorkoutLogsByStatus(status);
    }

    public List<WorkoutLog> findWorkoutLogsByTotalCaloriesBurntLessThan(String caloriesBurnt) {
        return workoutLogRepository.findWorkoutLogsByTotalCaloriesBurntLessThan(caloriesBurnt);
    }

    public List<WorkoutLog> findAllWorkoutLogs() {
        return workoutLogRepository.findAll();
    }

    public void deleteWorkoutLogById(Long workoutLogId) {
        workoutLogRepository.deleteById(workoutLogId);
    }

    @Transactional
    public WorkoutLog addWorkoutLog(Long memberId, String date, String duration, String status, String caloriesBurnt){
        entityManager.createNativeQuery("INSERT INTO workoutlog (member_id, workout_log_date, workout_log_duration, workout_log_status, workout_log_totalcaloriesburnt) " +
            "VALUES (?, ?, ?, ?, ?)")
            .setParameter(1, memberId)
            .setParameter(2, date)
            .setParameter(3, duration)
            .setParameter(4, status)
            .setParameter(5, caloriesBurnt)
            .executeUpdate();
        return new WorkoutLog();
    }

   
}
