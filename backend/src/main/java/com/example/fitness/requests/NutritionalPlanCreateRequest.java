package com.example.fitness.requests;

import com.example.fitness.components.Member;
import com.example.fitness.components.User;

import lombok.Data;

@Data
public class NutritionalPlanCreateRequest {
    Long nutritionalPlanId;
    User member;
    int totalCalorie;

}
