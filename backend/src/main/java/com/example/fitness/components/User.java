package com.example.fitness.components;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user",
    uniqueConstraints = {
        @UniqueConstraint(name = "id_unique", columnNames = "id"),
        @UniqueConstraint(name = "username_unique", columnNames = "username")
    } )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

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

    @Transient
    private Integer age;

    // Getter methods
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getMail() {
        return mail;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Integer getAge() {
        if (birthdate == null) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

}

