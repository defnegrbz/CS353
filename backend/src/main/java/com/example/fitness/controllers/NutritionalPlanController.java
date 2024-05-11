package com.example.fitness.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.NutritionalPlan;
import com.example.fitness.requests.NutritionalPlanCreateRequest;
import com.example.fitness.requests.NutritionalPlanUpdateRequest;
import com.example.fitness.services.NutritionalPlanService;

@RestController
@RequestMapping("/nutritionalPlan")
public class NutritionalPlanController {
    private NutritionalPlanService nutritionalPlanService;

    public NutritionalPlanController(NutritionalPlanService nutritionalPlanService) {
        this.nutritionalPlanService = nutritionalPlanService;
    }

    @GetMapping("/{nutritionalPlanId}")
    public NutritionalPlan getOneNutritionalPlan(@PathVariable Long nutritionalPlanId){
        return nutritionalPlanService.getOneNutritionalPlanById(nutritionalPlanId);
    }

    @PostMapping
    public NutritionalPlan createOneNutritionalPlan(@RequestBody NutritionalPlanCreateRequest newNutrientPlanRequest){
        return nutritionalPlanService.createOneNutritionalPlan(newNutrientPlanRequest);
    }

    @PutMapping("/{nutritionalPlanId}")
    public NutritionalPlan updateOneNutritionalPlan(@PathVariable Long nutritionalPlanId, @RequestBody NutritionalPlanUpdateRequest nutPlanUpdateRequest) {
        return nutritionalPlanService.updateOneNutritionalPlanById(nutritionalPlanId, nutPlanUpdateRequest);
    }

    @DeleteMapping("/{nutritionalPlanId}")
    public void deleteOneNutritionalPlan(@PathVariable Long nutritionalPlanId){
        nutritionalPlanService.deleteOneNutritionalPlan(nutritionalPlanId);
    }
}
