package com.example.fitness.components;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "workoutlog")
@Data
public class WorkoutLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_log_id")
    private Long workoutLogId;

    @Column(name = "workout_log_date")
    private String workoutLogDate;

    @Column(name = "workout_log_duration")
    private String workoutLogDuration;

    @Column(name = "workout_log_status")
    private String workoutLogStatus;

    @Column(name = "workout_log_totalcaloriesburnt")
    private String workoutLogTotalCaloriesBurnt;

    @Column(name = "id", insertable = false, updatable = false)
    private Long memberId;

    @Column(name = "workoutid", insertable = false, updatable = false)
    private Long workoutId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workoutid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Workout workout;
}
