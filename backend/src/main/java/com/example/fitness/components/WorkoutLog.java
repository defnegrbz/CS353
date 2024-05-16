package com.example.fitness.components;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "workoutlog")
@Data
public class WorkoutLog {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_log_id")
    Long workoutLogId;

    @Column(name = "workout_log_date")
    String workoutLogDate;

    @Column(name = "workout_log_duration")
    String workoutLogDuration;

    @Column(name = "workout_log_status")
    String workoutLogStatus;

    @Column(name = "workout_log_totalcaloriesburnt")
    String workoutLogTotalCaloriesBurnt;

    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;

    @Column(name = "workout_id", insertable = false, updatable = false)
    private Long workoutId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Workout workout;



    public WorkoutLog() {
    }

    public WorkoutLog(String workoutLogDate, String workoutLogDuration, String workoutLogStatus, String workoutLogTotalCaloriesBurnt, Long memberId, Long workoutId) { 
        this.workoutLogDate = workoutLogDate;
        this.workoutLogDuration = workoutLogDuration;
        this.workoutLogStatus = workoutLogStatus;
        this.workoutLogTotalCaloriesBurnt = workoutLogTotalCaloriesBurnt;
        this.memberId = memberId;
        this.workoutId = workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setWorkoutLogId(Long workoutLogId) {
        this.workoutLogId = workoutLogId;
    }

    public Long getWorkoutLogId() {
        return workoutLogId;
    }

    public void setWorkoutLogDate(String workoutLogDate) {
        this.workoutLogDate = workoutLogDate;
    }

    public String getWorkoutLogDate() {
        return workoutLogDate;
    }

    public void setWorkoutLogDuration(String workoutLogDuration) {
        this.workoutLogDuration = workoutLogDuration;
    }

    public String getWorkoutLogDuration() {
        return workoutLogDuration;
    }

    public void setWorkoutLogStatus(String workoutLogStatus) {
        this.workoutLogStatus = workoutLogStatus;
    }

    public String getWorkoutLogStatus() {
        return workoutLogStatus;
    }

    public void setWorkoutLogTotalCaloriesBurnt(String workoutLogTotalCaloriesBurnt) {
        this.workoutLogTotalCaloriesBurnt = workoutLogTotalCaloriesBurnt;
    }

    public String getWorkoutLogTotalCaloriesBurnt() {
        return workoutLogTotalCaloriesBurnt;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }
    


    
}
