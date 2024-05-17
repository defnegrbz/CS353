package com.example.fitness.requests;

import java.time.LocalDate;

import com.example.fitness.components.Member;

import com.example.fitness.components.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class NutrientLogUpdateRequest {
    @JsonIgnore
    Long nutrientLogId;
    Long memberId;
    LocalDate nutrientLogDate;
 
}
