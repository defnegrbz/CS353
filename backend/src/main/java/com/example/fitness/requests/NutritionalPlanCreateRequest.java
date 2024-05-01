package com.example.fitness.requests;

import lombok.Data;

@Data
public class NutritionalPlanCreateRequest {
    Long nutritionalPlanId;
    Member member;
    int totalCalorie;

}
