package com.example.fitness.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.Nutrient;
import com.example.fitness.services.NutrientService;

@RestController
@RequestMapping("/nutrients")
public class NutrientController {
    private NutrientService nutrientService;

    public NutrientController(NutrientService nutrientService){
        this.nutrientService = nutrientService;
    }

    @GetMapping
    public List<Nutrient> getAllNutrients(){
        return nutrientService.getAllNutrients();
    }

    @PostMapping
    public Nutrient createNutrient(@RequestBody Nutrient newNutrient){
        return nutrientService.addOneNutrient(newNutrient);
    }

    @GetMapping("/{nutrientId}")
    public Nutrient getOneNutrient(@PathVariable Long nutrientId){
        //need exception
        return nutrientService.getOneNutrient(nutrientId);
    }

    @PutMapping("/{nutrientId}")
    public Nutrient updateOneNutrient(@PathVariable Long nutrientId, @RequestBody Nutrient newNutrient){
        return nutrientService.updateOneNutrient(nutrientId, newNutrient);
    }

    @DeleteMapping("/{nutrientId}")
    public void deleteOneNutrient(@PathVariable Long nutrientId){
        nutrientService.deleteById(nutrientId);
    }
}
  