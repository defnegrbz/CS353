package com.example.fitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.components.Nutrient;

public interface NutrientRepository extends JpaRepository<Nutrient, Long>{
    
}
