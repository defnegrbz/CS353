package com.example.fitness.services;

import com.example.fitness.components.Workout;
import com.example.fitness.components.WorkoutLog;
import com.example.fitness.repositories.WorkoutLogRepository;
import com.example.fitness.requests.WorkoutLogRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutLogService {

    
    private static final Logger logger = LogManager.getLogger(WorkoutLogService.class);

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
    public WorkoutLog addWorkoutLog(Long memberId, WorkoutLog workoutLog) {
        logger.debug("Adding workout log for member ID: {}", memberId);
        logger.debug("Received workout log details: {}", workoutLog);

        if (memberId == null) {
            logger.error("memberId is null");
            throw new IllegalArgumentException("All critical workout log information must be provided");
        }

        try {
            entityManager.createNativeQuery("INSERT INTO workoutlog (id, workout_log_date, workout_log_duration, workout_log_status, workout_log_totalcaloriesburnt, workoutid) " +
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

    

    @Transactional
    public void deleteWorkoutLog(Long id) {
        logger.debug("Deleting workout log with ID: {}", id);

        if (id == null) {
            logger.error("Workout log ID is null");
            throw new IllegalArgumentException("Workout log ID must be provided");
        }

        try {
            int rowsAffected = entityManager.createNativeQuery("DELETE FROM workoutlog WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
            
            if (rowsAffected > 0) {
                logger.debug("Workout log deleted successfully for ID: {}", id);
            } else {
                logger.warn("No workout log found with ID: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error deleting workout log: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete workout log", e);
        }
    }

    @Transactional
    public WorkoutLog updateWorkoutLog(Long workoutLogId, WorkoutLogRequest workoutLogRequest) {
        logger.debug("Updating workout log with ID: {}", workoutLogId);
        logger.debug("Received updated workout log details: {}", workoutLogRequest);
    
        if (workoutLogId == null) {
            logger.error("Workout log ID is null");
            throw new IllegalArgumentException("Workout log ID must be provided");
        }
    
        try {
            int rowsAffected = entityManager.createNativeQuery("UPDATE workoutlog SET workout_log_date = ?, workout_log_duration = ?, workout_log_status = ?, workout_log_totalcaloriesburnt = ?, workout_id = ? WHERE workout_log_id = ?")
                .setParameter(1, workoutLogRequest.getDate())
                .setParameter(2, workoutLogRequest.getDuration())
                .setParameter(3, workoutLogRequest.getStatus())
                .setParameter(4, workoutLogRequest.getCaloriesBurnt())
                .setParameter(5, workoutLogRequest.getWorkoutId())
                .setParameter(6, workoutLogId)
                .executeUpdate();
            
            if (rowsAffected > 0) {
                logger.debug("Workout log updated successfully for ID: {}", workoutLogId);
                // Fetch the updated workout log from the database
                WorkoutLog updatedWorkoutLog = entityManager.find(WorkoutLog.class, workoutLogId);
                return updatedWorkoutLog;
            } else {
                logger.warn("No workout log found with ID: {}", workoutLogId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error updating workout log with ID: {}: {}", workoutLogId, e.getMessage(), e);
            throw new RuntimeException("Failed to update workout log", e);
        }
    }

}

