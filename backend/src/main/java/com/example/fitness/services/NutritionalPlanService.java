package com.example.fitness.services;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.NutritionalPlan;
import com.example.fitness.components.User;
import com.example.fitness.repositories.NutritionalPlanRepository;
import com.example.fitness.requests.NutritionalPlanCreateRequest;
import com.example.fitness.requests.NutritionalPlanUpdateRequest;

@Service
public class NutritionalPlanService {

    private NutritionalPlanRepository nutritionalPlanRepository;
    private UserService memberService;

    
    public NutritionalPlanService(NutritionalPlanRepository nutritionalPlanRepository, UserService memberService) {
        this.nutritionalPlanRepository = nutritionalPlanRepository;
        this.memberService = memberService;
    }

    public NutritionalPlan getOneNutritionalPlanById(Long nutritionalPlanId) {
        return nutritionalPlanRepository.findById(nutritionalPlanId).orElse(null);
    }

    public NutritionalPlan createOneNutritionalPlan(NutritionalPlanCreateRequest newNutrientPlanRequest) {
        User member = memberService.getMember(newNutrientPlanRequest.getMemberId());

        if(member == null){
          return null;
        }

        NutritionalPlan toSave = new NutritionalPlan();
        toSave.setMember(memberService.getMember(newNutrientPlanRequest.getMemberId()));
        toSave.setTotalCalorie(newNutrientPlanRequest.getTotalCalorie());
        toSave.setNutPlanDescription(newNutrientPlanRequest.getNutPlanDescription());
        toSave.setNutPlanTitle(newNutrientPlanRequest.getNutPlanTitle());
       
        nutritionalPlanRepository.saveOneNutritionalPlan(newNutrientPlanRequest.getMemberId(), newNutrientPlanRequest.getTotalCalorie(), newNutrientPlanRequest.getNutPlanDescription(), newNutrientPlanRequest.getNutPlanTitle());
        return toSave;
    }

    public NutritionalPlan updateOneNutritionalPlanById(Long nutritionalPlanId, NutritionalPlanUpdateRequest updateNutPlan){
        Optional<NutritionalPlan> nutPlan = nutritionalPlanRepository.findById(nutritionalPlanId);

        if(nutPlan.isPresent()){
            NutritionalPlan toUpdate = nutPlan.get();
            toUpdate.setTotalCalorie(updateNutPlan.getTotalCalorie());
            toUpdate.setNutPlanDescription(updateNutPlan.getNutPlanDescription());
            toUpdate.setNutPlanTitle(updateNutPlan.getNutPlanTitle());
            nutritionalPlanRepository.saveOneNutritionalPlan(updateNutPlan.getMemberId(), toUpdate.getTotalCalorie(), toUpdate.getNutPlanDescription(), toUpdate.getNutPlanTitle());
            return toUpdate;
        }
        return null;
    }

    public void deleteById(Long nutritionalPlanId) {
        nutritionalPlanRepository.deleteById(nutritionalPlanId);
    }
    
}
