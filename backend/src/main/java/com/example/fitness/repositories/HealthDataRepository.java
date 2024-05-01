package com.example.fitness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fitness.components.HealthData;

public interface HealthDataRepository extends JpaRepository<HealthData, Long>{


    // @Query(value = "SELECT * FROM healthData hd WHERE hd.userId =?1", nativeQuery = true)
    // List<HealthData> findByUserId(Long userId);

}