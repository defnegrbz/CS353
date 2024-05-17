package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.Nutrient;
import com.example.fitness.repositories.NutrientRepository;

@Service
public class NutrientService {
    
    private NutrientRepository nutrientRepository;

    public NutrientService(NutrientRepository nutrientRepository) {
        this.nutrientRepository = nutrientRepository;
    }

    public List<Nutrient> getAllNutrients() {
        return nutrientRepository.findAll();
    }

    public Nutrient addOneNutrient(Nutrient newNutrient) {
        nutrientRepository.addOneNutrient(newNutrient.getNutrientName(), newNutrient.getNutrientQuantity(), newNutrient.getNutrientCalorie());
        return newNutrient;
        
    }

    public Nutrient getOneNutrient(Long nutrientId) {
        return nutrientRepository.findById(nutrientId).orElse(null);
    }

    public Nutrient updateOneNutrient(Long nutrientId, Nutrient newNutrient) {
        Optional<Nutrient> nutrient = nutrientRepository.findById(nutrientId);
        if(nutrient.isPresent()){
            Nutrient found = nutrient.get();
            found.setNutrientCalorie(newNutrient.getNutrientCalorie());
            found.setNutrientName(newNutrient.getNutrientName());
            found.setNutrientQuantity(newNutrient.getNutrientQuantity()); 
             
            nutrientRepository.addOneNutrient(newNutrient.getNutrientName(), newNutrient.getNutrientQuantity(), newNutrient.getNutrientCalorie());
            return found;
        }else{ //need exception

            return null;
        }
    }

    public void deleteById(Long nutrientId) {
        nutrientRepository.deleteById(nutrientId);
    }

    
}
