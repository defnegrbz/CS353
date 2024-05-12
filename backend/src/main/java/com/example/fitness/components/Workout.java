package com.example.fitness.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainerid", referencedColumnName = "id", insertable = false, updatable = false) // referencedColumnName = "userid" / Trainer trainer 
    private Trainer trainer;    

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
    private Integer calorieBurnPerUnitTime;
    
    @Column(name = "intensity_level")
    private Integer intensityLevel;

    public Workout() {
    }

    public Workout(Long trainerID, String workoutTitle, String workoutType, String targetAudience,
                   Integer workoutEstimatedTime, String workoutDescription, String equipments, Integer calorieBurnPerUnitTime, Integer intensityLevel) {
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

    public Integer getWorkoutEstimatedTime() {
        return workoutEstimatedTime;
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

    public Integer getCalorieBurnPerUnitTime() {
        return calorieBurnPerUnitTime;
    }

    public void setCalorieBurnPerUnitTime(Integer calorieBurnPerUnitTime) {
        this.calorieBurnPerUnitTime = calorieBurnPerUnitTime;
    }

    public Integer getIntensityLevel() {
        return intensityLevel;
    }

    public void setWorkoutEstimatedTime(Integer workoutEstimatedTime) {
        this.workoutEstimatedTime = workoutEstimatedTime;
    }
    
    public void setIntensityLevel(Integer intensityLevel) {
        this.intensityLevel = intensityLevel;
    }
    
}
