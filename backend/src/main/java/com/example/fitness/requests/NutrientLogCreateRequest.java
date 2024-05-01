package com.example.fitness.requests;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NutrientLogCreateRequest {
    
    Long nutrientLogId;
    Member member;
    String nutrientLogType;
    DateTimeFormat nutrientLogDate;
}
