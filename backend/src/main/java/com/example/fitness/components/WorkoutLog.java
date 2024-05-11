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
    LocalDate workoutLogDate;

    @Column(name = "workout_log_duration")
    String workoutLogDuration;

    @Column(name = "workout_log_status")
    String workoutLogStatus;

    @Column(name = "workout_log_totalcaloriesburnt")
    String workoutLogTotalCaloriesBurnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Member member;

    
}
