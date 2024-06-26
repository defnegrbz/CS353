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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.NutrientLog;
import com.example.fitness.requests.NutrientLogCreateRequest;
import com.example.fitness.requests.NutrientLogUpdateRequest;
import com.example.fitness.services.NutrientLogService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/nutrientLog")
public class NutrientLogController {
    private NutrientLogService nutrientLogService;

    public NutrientLogController(NutrientLogService nutrientLogService) {
        this.nutrientLogService = nutrientLogService;
    }
    
    @GetMapping
    public List<NutrientLog> getAllNutrientLogs(@RequestParam Long member_id){
        return nutrientLogService.getAllNutrientLogs(member_id);
    }

    @GetMapping("/{nutrientLogId}")
    public NutrientLog getOneNutrientLog(@PathVariable Long nutrientLogId){
        return nutrientLogService.getOneNutrientLogById(nutrientLogId);
    }

    @PostMapping
    public NutrientLog createOneNutrientLog(@RequestBody NutrientLogCreateRequest newNutrientLogRequest){
        return nutrientLogService.createOneNutrientLog(newNutrientLogRequest);
    }

    @PutMapping("/{nutrientLogId}")
    public NutrientLog updateOneNutrientLog(@PathVariable Long nutrientLogId, @RequestBody NutrientLogUpdateRequest nutLogUpdateRequest) {
        return nutrientLogService.updateOneNutrientLogById(nutrientLogId, nutLogUpdateRequest);
    }

    @DeleteMapping("/{nutrientLogId}")
    public void deleteOneNutrienLog(@PathVariable Long nutrientLogId){
        nutrientLogService.deleteOneNutrienLogById(nutrientLogId);
    }

    @PostMapping("/{nutrientLogId}/addNutrients")
    public NutrientLog addNutrientsToLog(@PathVariable Long nutrientLogId, @RequestBody List<Long> nutrientIds) {
        NutrientLog updatedLog = nutrientLogService.addNutrientsToLog(nutrientLogId, nutrientIds);
        return updatedLog;
    }
}
