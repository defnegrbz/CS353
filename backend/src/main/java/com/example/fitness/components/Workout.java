package com.example.fitness.components;

import jakarta.persistence.*;

@Entity(name = "Workout")
@Table(
        name = "workout",
        uniqueConstraints = {
            @UniqueConstraint(name = "workoutID_unique", columnNames = "workoutID"),
            @UniqueConstraint(name = "workoutTitle_unique", columnNames = "workout_title")
        }
)
public class Workout {
    public static Long WorkoutID = 1000L;

    // properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workoutID")
    private Long workoutID;

    @Column(name = "trainerID")
    private Long trainerID;

    @Column(name = "workout_title", nullable = false)
    private String workoutTitle;

    @Column(name = "workout_type", nullable = false)
    private String workoutType;

    @Column(name = "target_audience", nullable = false)
    private String targetAudience;

    @Column(name = "workout_count")
    private int workoutCount;

    @Column(name = "workout_estimated_time")
    private int workoutEstimatedTime;

    @Column(name = "workout_description", columnDefinition = "TEXT")
    private String workoutDescription;

    @Column(name = "equipments", columnDefinition = "TEXT")
    private String equipments;

    @Column(name = "calorie_burn_per_unit_time")
    private double calorieBurnPerUnitTime;

    @Column(name = "intensity_level")
    private int intensityLevel;

    // constructors
    public Workout() {
    }

    public Workout(Long trainerID, String workoutTitle, String workoutType, String targetAudience, int workoutCount,
                   int workoutEstimatedTime, String workoutDescription, String equipments, double calorieBurnPerUnitTime, int intensityLevel) {
        this.trainerID = trainerID;
        this.workoutTitle = workoutTitle;
        this.workoutType = workoutType;
        this.targetAudience = targetAudience;
        this.workoutCount = workoutCount;
        this.workoutEstimatedTime = workoutEstimatedTime;
        this.workoutDescription = workoutDescription;
        this.equipments = equipments;
        this.calorieBurnPerUnitTime = calorieBurnPerUnitTime;
        this.intensityLevel = intensityLevel;
    }

    // getters and setters
    public Long getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(Long workoutID) {
        this.workoutID = workoutID;
    }

    public Long getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Long trainerID) {
        this.trainerID = trainerID;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public int getWorkoutCount() {
        return workoutCount;
    }

    public void setWorkoutCount(int workoutCount) {
        this.workoutCount = workoutCount;
    }

    public int getWorkoutEstimatedTime() {
        return workoutEstimatedTime;
    }

    public void setWorkoutEstimatedTime(int workoutEstimatedTime) {
        this.workoutEstimatedTime = workoutEstimatedTime;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public double getCalorieBurnPerUnitTime() {
        return calorieBurnPerUnitTime;
    }

    public void setCalorieBurnPerUnitTime(double calorieBurnPerUnitTime) {
        this.calorieBurnPerUnitTime = calorieBurnPerUnitTime;
    }

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }
}

