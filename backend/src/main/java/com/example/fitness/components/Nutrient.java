package com.example.fitness.components;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="nutrient")
@Data
public class Nutrient {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    Long nutrientID;
    
    @Column(columnDefinition = "nutrient_name")
    String nutrientName;

    @Column(columnDefinition = "nutrient_quantity")
    int nutrientQuantity;

    @Column(columnDefinition = "nutrient_calorie")
    int nutrientCalorie;

}
