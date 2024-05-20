package com.example.fitness.components;

import jakarta.persistence.*;

@Entity
@Table(name = "WorkoutCatalog")
public class WorkoutCatalog {

    @Id
    private Long workoutID;

    private Long trainerID;
    private String workoutTitle;
    private String workoutType;
    private String targetAudience;
    private Integer workoutEstimatedTime;
    private String workoutDescription;
    private String equipments;
    private Integer calorieBurnPerUnitTime;
    private Integer intensityLevel;
}

