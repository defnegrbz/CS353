package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

}