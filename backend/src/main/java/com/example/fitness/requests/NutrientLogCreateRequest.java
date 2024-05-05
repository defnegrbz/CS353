package com.example.fitness.requests;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.fitness.components.User;

import lombok.Data;

@Data
public class NutrientLogCreateRequest {
    
    Long nutrientLogId;
    User member;
    String nutrientLogType;
    DateTimeFormat nutrientLogDate;
}
