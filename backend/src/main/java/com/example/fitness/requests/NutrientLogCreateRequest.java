package com.example.fitness.requests;

import java.time.LocalDate;

import com.example.fitness.components.Member;

import com.example.fitness.components.User;

import lombok.Data;

@Data
public class NutrientLogCreateRequest {
    
    Long nutrientLogId;
    User member;
    String nutrientLogType;
    LocalDate nutrientLogDate;
}
