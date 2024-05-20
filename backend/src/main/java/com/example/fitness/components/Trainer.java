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


    // Storing the votes of the trainer as the number of votes and the total score of the votes initially 0
    @Column(name = "voteScore")
    private Integer voteScore;
    
    @Column(name = "voteCount")
    private Integer voteCount;


    

}
