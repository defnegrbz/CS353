package com.example.fitness.requests;

import com.example.fitness.components.Member;
import com.example.fitness.components.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class NutritionalPlanUpdateRequest {
    @JsonIgnore
    Long nutritionalPlanId;
    Long memberId;
    int totalCalorie;

}
