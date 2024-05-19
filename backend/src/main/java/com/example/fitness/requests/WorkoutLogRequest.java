package com.example.fitness.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class WorkoutLogRequest {
    private Long member_id;
    private String workout_log_date;
    private String workout_log_duration;
    private String workout_log_status;
    private String workout_log_totalcaloriesburnt;
    private Long workout_id;

    
    // Getters and setters

    public Long getWorkoutId() {
        return workout_id;
    }

    public Long getMemberId() {
        return member_id;
    }

    public void setMemberId(Long memberId) {
        this.member_id = memberId;
    }

    public String getDate() {
        return workout_log_date;
    }

    public void setDate(String date) {
        this.workout_log_date = date;
    }

    public String getDuration() {
        return workout_log_duration;
    }

    public void setDuration(String duration) {
        this.workout_log_duration = duration;
    }

    public String getStatus() {
        return workout_log_status;
    }

    public void setStatus(String status) {
        this.workout_log_status = status;
    }

    public String getCaloriesBurnt() {
        return workout_log_totalcaloriesburnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        this.workout_log_totalcaloriesburnt = caloriesBurnt;
    }

    public void setWorkoutId(Long workoutId) {
        this.workout_id = workoutId;
    }
}
