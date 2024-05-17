package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.Nutrient;
import com.example.fitness.components.NutrientLog;

import jakarta.transaction.Transactional;

public interface NutrientLogRepository extends JpaRepository<NutrientLog, Long>{

    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.member_id = ?1", nativeQuery = true)
    List<NutrientLog> findByMemberId(Long memberId);
    
    @Query(value = "SELECT * FROM nutrient_log nl", nativeQuery = true)
    List<NutrientLog> findAll();

    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.nutrient_log_id = ?1", nativeQuery = true)
    Optional<NutrientLog> findById(Long nutrientLogId);

    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.nutrient_log_date = ?1", nativeQuery = true)
    Optional<NutrientLog> findByDate(LocalDate nutrientLogDate);
    


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutrient_log nl WHERE nl.nutrient_log_id = ?1", nativeQuery = true)
    void deleteById(Long nutrientLogId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nutrient_log (member_id, nutrient_log_date) " +
                   "VALUES (:member_id, :nutrient_log_date)", nativeQuery = true)
    void saveOneNutrientLog(@Param("member_id") Long member_id, @Param("nutrient_log_date") LocalDate nutrient_log_date);
    
    
}
