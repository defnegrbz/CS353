package com.example.fitness.requests;

import com.example.fitness.components.Member;

import lombok.Data;

@Data
public class NutritionalPlanCreateRequest {
    Long nutritionalPlanId;
    Member member;
    int totalCalorie;

}
