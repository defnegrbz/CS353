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

    @Query(value = "SELECT * FROM nutrient n WHERE n.nutrient_id = ?1", nativeQuery = true)
    Optional<Nutrient> findById(Long nutrientID);

    @Query(value = "SELECT * FROM nutrient n WHERE n.nutrient_name = ?1", nativeQuery = true)
    Optional<Nutrient> findNutrientByName(String name);

    @Query(value = "SELECT n FROM nutrient n WHERE n.nutrient_calorie = ?1", nativeQuery = true)
    List<Nutrient> findNNutrientByCalorie(int nutrientCalorie);

    @Query(value = "SELECT n FROM nutrient n WHERE n.nutrient_quantity = ?1", nativeQuery = true)
    List<Nutrient> findNNutrientByQuantity(int nutrientQuantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutrient n WHERE n.nutrient_id = ?1", nativeQuery = true)
    void deleteById(Long nutrientID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nutrient (nutrient_name, nutrient_quantity, nutrient_calorie) " +
                    "VALUES (:nutrient_name, :nutrient_quantity, :nutrient_calorie)", nativeQuery = true)
    void addOneNutrient(@Param("nutrient_name") String nutrient_name, @Param("nutrient_quantity") Integer nutrient_quantity, @Param("nutrient_calorie") Integer nutrient_calorie);



    
}
