package com.example.fitness.components;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "nutrientLog")
@Data
public class NutrientLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_log_id")
    @JsonIgnore
    Long nutrientLogId;

    @ManyToMany
    @JoinTable(
    name = "nutrient_log_has", 
    joinColumns = @JoinColumn(name = "nutrient_log_id"), 
    inverseJoinColumns = @JoinColumn(name = "nutrient_id"))
    List<Nutrient> includedNutrients;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User member;

    @Column(name = "nutrient_log_date")
    LocalDate nutrientLogDate;

    @Transient
    int totalCalories;

    public Integer getTotalCalories() {
        int total = 0;
        for (Nutrient nutrient : includedNutrients) {
            total += nutrient.getNutrientCalorie()*nutrient.getNutrientQuantity();
        }
        return total;
    }

}
