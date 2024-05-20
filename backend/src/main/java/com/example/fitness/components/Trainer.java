package com.example.fitness.components;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Trainer")
public class Trainer extends User{

    // Storing busy dates of the trainer as a LocalDate list
    @ElementCollection
    @CollectionTable(name = "trainer_busyDates", joinColumns = @JoinColumn(name = "userID"))
    @Column(name = "busyDates")
    private List<LocalDate> busyDates;

    @Column(name = "trainerDescription", columnDefinition = "TEXT")
    private String trainerDescription;

    @Column(name = "specialization", columnDefinition = "TEXT")
    private String specialization;

    @Column(name = "trainerExperience")
    private Integer trainerExperience;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "trainerRating")
    private Double trainerRating;
}
