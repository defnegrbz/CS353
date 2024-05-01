package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.components.NutritionalPlan;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long>{
    List<NutritionalPlan> findByMemberId(Optional<Long> member_id);

}
