package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.components.NutrientLog;

public interface NutrientLogRepository extends JpaRepository<NutrientLog, Long>{

    List<NutrientLog> findByMemberId(Optional<Long> member_id);
    
    
}
