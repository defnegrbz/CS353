package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.Nutrient;

import jakarta.transaction.Transactional;

public interface NutrientRepository extends JpaRepository<Nutrient, Long>{
    
    @Query(value = "SELECT * FROM nutrient n", nativeQuery = true)
    List<Nutrient> findAll();

    @Query(value = "SELECT * FROM nutrient n WHERE n.nutrientID = ?1", nativeQuery = true)
    Optional<Nutrient> findById(Long nutrientID);

    @Query(value = "SELECT * FROM nutrient n WHERE n.nutrientName = ?1", nativeQuery = true)
    Optional<Nutrient> findNutrientByName(String name);

    @Query("SELECT n FROM Nutrient n WHERE n.nutrientCalorie <= :nutrientCalorie")
    List<Nutrient> findNNutrientByCalorie(@Param("nutrientCalorie") int nutrientCalorie);

    @Query("SELECT n FROM Nutrient n WHERE n.nutrientQuantity <= :nutrientQuantity")
    List<Nutrient> findNNutrientByQuantity(@Param("nutrientQuantity") int nutrientQuantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutrient n WHERE n.nutrientID = ?1", nativeQuery = true)
    void deleteById(Long nutrientID);

    
}
