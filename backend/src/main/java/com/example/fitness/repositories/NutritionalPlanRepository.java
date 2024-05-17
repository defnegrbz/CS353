package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.NutrientLog;
import com.example.fitness.components.NutritionalPlan;

import jakarta.transaction.Transactional;

public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long>{
    List<NutritionalPlan> findByMemberId(Optional<Long> member_id);

    @Query(value = "SELECT * FROM nutritional_plan np WHERE np.nutritional_plan_id = ?1", nativeQuery = true)
    Optional<NutritionalPlan> findByID(Long nutritionalPlanId);

    @Query(value = "SELECT np FROM nutritional_plan np WHERE np.member_id = ?1", nativeQuery = true)
    List<NutritionalPlan> findByMember(Long memberId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutritional_plan np WHERE nutritional_plan_id = ?1", nativeQuery = true)
    void deleteById(Long nutritionalPlanId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO nutritional_plan (member_id, total_calorie, nut_plan_description, nut_plan_title) " +
                   "VALUES (:member_id, :total_calorie, :nut_plan_description, :nut_plan_title)", nativeQuery = true)
    void saveOneNutritionalPlan(@Param("member_id") Long member_id, @Param("total_calorie")  int total_calorie, @Param("nut_plan_description") String nut_plan_description, @Param("nut_plan_title") String nut_plan_title);


}
