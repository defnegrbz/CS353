package com.example.fitness.components;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity(name = "Workout")
@Table(name = "workout")
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workoutid")
    private Long workoutID;

    @Column(name = "trainerid")
    private Long trainerID;  // This field will be stored in the Workout table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Trainer trainer;  // This field establishes the foreign key relationship

    @Column(name = "workout_title", nullable = false)
    private String workoutTitle;

    @Column(name = "workout_type", nullable = false)
    private String workoutType;

    @Column(name = "target_audience", nullable = false)
    private String targetAudience;

    @Column(name = "workout_estimated_time")
    private Integer workoutEstimatedTime;

    @Column(name = "workout_description", columnDefinition = "TEXT")
    private String workoutDescription;

    @Column(name = "equipments", columnDefinition = "TEXT")
    private String equipments;

    @Column(name = "calorie_burn_per_unit_time")
    private Double calorieBurnPerUnitTime;

    @Column(name = "intensity_level")
    private Integer intensityLevel;

    public Workout() {
    }

    public Workout(Long trainerID, String workoutTitle, String workoutType, String targetAudience, Integer workoutCount,
                   Integer workoutEstimatedTime, String workoutDescription, String equipments, Double calorieBurnPerUnitTime, Integer intensityLevel) {
        this.trainerID = trainerID;
        this.workoutTitle = workoutTitle;
        this.workoutType = workoutType;
        this.targetAudience = targetAudience;
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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
