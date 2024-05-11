package com.example.fitness.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.fitness.components.NutritionalPlan;

import jakarta.transaction.Transactional;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long>{
    List<NutritionalPlan> findByMemberId(Optional<Long> member_id);

    @Query(value = "SELECT * FROM nutritionalPlan np WHERE np.nutritionalPlanId = ?1", nativeQuery = true)
    Optional<NutritionalPlan> findByID(Long nutritionalPlanId);

    @Query("SELECT np FROM nutritionalPlan np WHERE np.memberId = memberId")
    List<NutritionalPlan> findByMember(@Param("memberId") Long memberId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutritionalPlan np WHERE nutritionalPlan = ?1", nativeQuery = true)
    void deleteById(Long nutritionalPlan);
}
