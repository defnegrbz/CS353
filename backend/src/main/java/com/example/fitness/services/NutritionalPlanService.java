package com.example.fitness.services;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.NutritionalPlan;
import com.example.fitness.repositories.NutritionalPlanRepository;
import com.example.fitness.requests.NutritionalPlanCreateRequest;
import com.example.fitness.requests.NutritionalPlanUpdateRequest;

@Service
public class NutritionalPlanService {

    private NutritionalPlanRepository nutritionalPlanRepository;
    private MemberService memberService;

    
    public NutritionalPlanService(NutritionalPlanRepository nutritionalPlanRepository, MemberService memberService) {
        this.nutritionalPlanRepository = nutritionalPlanRepository;
        this.memberService = memberService;
    }

    public NutritionalPlan getOneNutritionalPlanById(Long nutritionalPlanId) {
        return nutritionalPlanRepository.findById(nutritionalPlanId).orElse(null);
    }

    public NutritionalPlan createOneNutritionalPlan(NutritionalPlanCreateRequest newNutrientPlanRequest) {
        Member member memberService.getOneMember(newNutrientPlanRequest.getMemberId());

        if(member == null){
            return null;
        }

        NutritionalPlan toSave = new NutritionalPlan();
        toSave.setMember(newNutrientPlanRequest.getMember());
        toSave.setNutritionalPlanId(newNutrientPlanRequest.getNutritionalPlanId());
        toSave.setTotalCalorie(newNutrientPlanRequest.getTotalCalorie());
       
        return nutritionalPlanRepository.save(toSave);
    }

    public NutritionalPlan updateOneNutritionalPlanById(Long nutritionalPlanId, NutritionalPlanUpdateRequest updateNutPlan){
        Optional<NutritionalPlan> nutPlan = nutritionalPlanRepository.findById(nutritionalPlanId);

        if(nutPlan.isPresent()){
            NutritionalPlan toUpdate = nutPlan.get();
            toUpdate.setMember(updateNutPlan.getMember());
            toUpdate.setNutritionalPlanId(updateNutPlan.getNutritionalPlanId());
            toUpdate.setTotalCalorie(updateNutPlan.getTotalCalorie());
            nutritionalPlanRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneNutritionalPlan(Long nutritionalPlanId) {
        nutritionalPlanRepository.deleteById(nutritionalPlanId);
    }
    
}
