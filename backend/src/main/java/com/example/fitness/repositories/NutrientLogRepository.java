package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.NutrientLog;

import jakarta.transaction.Transactional;

public interface NutrientLogRepository extends JpaRepository<NutrientLog, Long>{

    @Query(value = "SELECT * FROM nutrientLog nl WHERE nl.member_id = ?1", nativeQuery = true)
    Optional<NutrientLog> findByMemberId(Long member_id);
    
    @Query(value = "SELECT * FROM nutrientLog nl", nativeQuery = true)
    List<NutrientLog> findAll();

    @Query(value = "SELECT * FROM nutrientLog nl WHERE nl.nutrientLogId = ?1", nativeQuery = true)
    Optional<NutrientLog> findById(Long nutrientLogId);

    @Query(value = "SELECT * FROM nutrientLog nl WHERE nl.nutrientLogDate = ?1", nativeQuery = true)
    Optional<NutrientLog> findByDate(LocalDate nutrientLogDate);
    
    @Query("SELECT nl FROM nutrientLog nl WHERE nl.nutrientLogType LIKE %:nutrientLogType%")
    List<NutrientLog> findByNutrientLogType(@Param("nutrientLogType") String nutrientLogType);

    @Query("SELECT nl FROM nutrientLog nl WHERE nl.memberId = :memberID")
    List<NutrientLog> findByMember(@Param("memberId") Long memberId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutrientLog nl WHERE nl.nutrientLogId = ?1", nativeQuery = true)
    void deleteById(Long nutrientLogId);
    
    
}
