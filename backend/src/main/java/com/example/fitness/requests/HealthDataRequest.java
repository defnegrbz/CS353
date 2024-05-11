package com.example.fitness.requests;

import lombok.Data;

@Data
public class HealthDataRequest {
    Long id;
    Integer height;
    Double weight;
    String allergies;
    String diseases;
    String medications;
    Double BMI;

}
