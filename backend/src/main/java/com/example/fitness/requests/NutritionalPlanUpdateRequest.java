package com.example.fitness.requests;

import lombok.Data;

@Data
public class NutritionalPlanUpdateRequest {
    Long nutritionalPlanId;
    Member member;
    int totalCalorie;

}
