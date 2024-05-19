package com.example.fitness.components;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.math.RoundingMode;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Member")
public class Member extends User{

    // Storing fitness goals as a string array
    // @ElementCollection
    // @CollectionTable(name = "member_fitnessGoals", joinColumns = @JoinColumn(name = "userID"))
    // @Column(name = "fitnessGoals")
    // private List<String> fitnessGoals;

    @Column(name = "sugCalorieIntake")
    private Integer sugCalorieIntake;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "diseases", columnDefinition = "TEXT")
    private String diseases;

    @Column(name = "medications", columnDefinition = "TEXT")
    private String medications;

    @Column(name = "fitness_goals", columnDefinition = "TEXT")
    private String fitness_goals;

    // Getter methods
    // public List<String> getFitnessGoals() {
    //     return fitnessGoals;
    // }

    // public Integer getSugCalorieIntake() {
    //     return sugCalorieIntake;
    // }

    // public Integer getHeight() {
    //     return height;
    // }

    // public Double getWeight() {
    //     return weight;
    // }

    // public String getAllergies() {
    //     return allergies;
    // }

    // public String getDiseases() {
    //     return diseases;
    // }

    // public String getMedications() {
    //     return medications;
    // }

    // public String getFitness_goals() {
    //     return fitness_goals;
    // }

    @Transient
    private Double BMI;

    // Getter method for BMI attribute
    public Double getBMI() {
        if (height == null || weight == null) {
            return null;
        }
        Double heightInMeters = ((double) height) / 100;
        Double BMI = weight / (heightInMeters * heightInMeters);

        // Round BMI to two decimal places
        BigDecimal roundedBMI = new BigDecimal(BMI).setScale(2, RoundingMode.HALF_UP);
        return roundedBMI.doubleValue();
    }
   
}
