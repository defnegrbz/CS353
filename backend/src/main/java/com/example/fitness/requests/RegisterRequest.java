package com.example.fitness.requests;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String username;
    private String password;
    private String gender;
    private String mail;
    private LocalDate birthdate;
    private Integer height;
    private Double weight;
    private String allergies;
    private String diseases;
    private String medications;
    private String fitnessGoals;
}