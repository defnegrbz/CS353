package com.example.fitness.components;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user",
    uniqueConstraints = {
        @UniqueConstraint(name = "id_unique", columnNames = "id"),
    } )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties("user") // Ignore user field in HealthData during serialization
    private HealthData healthData;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mail")
    private String mail;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "profilePicture")
    private String profilePicture;

    // Calculate age dynamically
    @Transient
    public Integer getAge() {
        if (birthdate == null) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }
}


