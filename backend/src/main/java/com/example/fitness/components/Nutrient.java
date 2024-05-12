package com.example.fitness.components;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="nutrient")
@Data
public class Nutrient {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    @JsonIgnore
    Long nutrientID;
    
    @Column(name = "nutrient_name")
    String nutrientName;

    @Column(name = "nutrient_quantity")
    int nutrientQuantity;

    @Column(name = "nutrient_calorie")
    int nutrientCalorie;

}
