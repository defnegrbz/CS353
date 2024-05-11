package com.example.fitness.components;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Member")
public class Member extends User{

    // public Member(String fullname, String username, String password, String gender, String mail, LocalDate birthDate,
    //         String profilePicture, List<String> fitnessGoals2, Integer sugCalorieIntake2) {
    // }

    // Storing fitness goals as a string array
    @ElementCollection
    @CollectionTable(name = "member_fitnessGoals", joinColumns = @JoinColumn(name = "userID"))
    @Column(name = "fitnessGoals")
    private List<String> fitnessGoals;

    @Column(name = "sugCalorieIntake")
    private Integer sugCalorieIntake;


}
