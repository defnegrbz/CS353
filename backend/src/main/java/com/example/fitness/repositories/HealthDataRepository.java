package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.HealthData;

import jakarta.transaction.Transactional;

public interface HealthDataRepository extends JpaRepository<HealthData, Long>{

    @Query(value = "SELECT * FROM health_data hd WHERE hd.user_id =?1", nativeQuery = true)
    Optional<HealthData> findByUserId(Long userId);

    @Query(value = "SELECT * FROM health_data hd", nativeQuery = true)
    List<HealthData> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM health_data hd WHERE hd.user_id =?1", nativeQuery = true)
    void deleteById(Long userID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO health_data (user_id, allergies, diseases, medications, height, weight) " +
                   "VALUES (:user_id, :allergies, :diseases, :medications, :height, :weight)", nativeQuery = true)
    void addHealthData(@Param("user_id") Long user_id, @Param("allergies") String allergies, @Param("diseases") String diseases, 
    @Param("medications") String medications, @Param("height") Integer height, @Param("weight") Double weight);

}