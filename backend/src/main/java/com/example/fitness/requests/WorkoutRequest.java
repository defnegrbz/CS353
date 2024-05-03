package com.example.fitness.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkoutRequest {
    private final Long trainerid;
    private final String workout_title;
    private final String workout_type;
    private final String target_audience;
    private final Integer workout_count;
    private final Integer workout_estimated_time;
    private final String workout_description;
    private final String equipments;
    private final Double calorie_burn_per_unit_time;
    private final Integer intensity_level;

    public String getWorkoutTitle() {
        return workout_title;
    }

    public String getWorkoutType() {
        return workout_type;
    }

    public String getTargetAudience() {
        return target_audience;
    }

    public Integer getWorkoutCount() {
        return workout_count;
    }


    public Integer getWorkoutEstimatedTime() {
        return workout_estimated_time;
    }


    public String getWorkoutDescription() {
        return workout_description;
    }


    public Double getCaloriesBurnPerUnitTime() {
        return calorie_burn_per_unit_time;
    }


    public Integer getIntensityLevel() {
        return intensity_level;
    }


    public String getEquipments() {
        return equipments;
    }
}



