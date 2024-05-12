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
    Optional<NutrientLog> findByMemberId(Long member_id);
    
    @Query(value = "SELECT * FROM nutrient_log nl", nativeQuery = true)
    List<NutrientLog> findAll();

    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.nutrient_log_id = ?1", nativeQuery = true)
    Optional<NutrientLog> findById(Long nutrientLogId);

    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.nutrient_log_date = ?1", nativeQuery = true)
    Optional<NutrientLog> findByDate(LocalDate nutrientLogDate);
    
    @Query(value = "SELECT * FROM nutrient_log nl WHERE nl.nutrient_log_type = ?1", nativeQuery = true)
    List<NutrientLog> findByNutrientLogType(String nutrientLogType);

    @Query(value = "SELECT nl FROM nutrient_log nl WHERE nl.member_id = ?1", nativeQuery = true)
    List<NutrientLog> findByMember(Long memberId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutrient_log nl WHERE nl.nutrient_log_id = ?1", nativeQuery = true)
    void deleteById(Long nutrientLogId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nutrient_log (member_id, nutrient_log_date, nutrient_log_type) " +
                   "VALUES (:member_id, :nutrient_log_date, :nutrient_log_type)", nativeQuery = true)
    NutrientLog saveOneNutrientLog(@Param("member_id") Long member_id, @Param("nutrient_log_date") LocalDate nutrient_log_date,  @Param("nutrient_log_type") String nutrient_log_type);
    
    
}
