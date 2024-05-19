package com.example.fitness.components;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "healthData")
public class HealthData {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;

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
