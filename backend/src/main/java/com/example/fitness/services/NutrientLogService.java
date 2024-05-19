package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.Nutrient;
import com.example.fitness.components.NutrientLog;
import com.example.fitness.components.User;
import com.example.fitness.repositories.NutrientLogRepository;
import com.example.fitness.repositories.NutrientRepository;
import com.example.fitness.requests.NutrientLogCreateRequest;
import com.example.fitness.requests.NutrientLogUpdateRequest;

import jakarta.transaction.Transactional;

@Service
public class NutrientLogService {
    private NutrientLogRepository nutrientLogRepository;
    private NutrientRepository nutrientRepository;
    private UserService memberService;

    public NutrientLogService(NutrientLogRepository nutrientLogRepository, UserService memberService, NutrientRepository nutrientRepository) {
        this.nutrientLogRepository = nutrientLogRepository;
        this.memberService = memberService;
        this.nutrientRepository = nutrientRepository;
    }

    public List<NutrientLog> getAllNutrientLogs(Long memberId) {

        if(memberService.getMember(memberId) != null){
            return nutrientLogRepository.findByMemberId(memberId);
        }
        return null;
        //return nutrientLogRepository.findAll();
    }

    public List<NutrientLog> getAllNutrientLogs() {
        return nutrientLogRepository.findAll();
    }

    public NutrientLog getOneNutrientLogById(Long nutrientLogId) {
        return nutrientLogRepository.findById(nutrientLogId).orElse(null);
    }

    public NutrientLog createOneNutrientLog(NutrientLogCreateRequest newNutrientLogRequest) {
        User member = memberService.getMember(newNutrientLogRequest.getMemberId());
        
        if(member == null){
            return null;
        }

        NutrientLog toSave = new NutrientLog();
        toSave.setNutrientLogId(newNutrientLogRequest.getNutrientLogId());
        toSave.setMember(memberService.getMember(newNutrientLogRequest.getMemberId()));
        toSave.setNutrientLogDate(newNutrientLogRequest.getNutrientLogDate());
    
        nutrientLogRepository.saveOneNutrientLog(newNutrientLogRequest.getMemberId(), newNutrientLogRequest.getNutrientLogDate()); 
        return toSave;
    }

    public NutrientLog updateOneNutrientLogById(Long nutrientLogId, NutrientLogUpdateRequest updateNutLog) {
        Optional<NutrientLog> nutLog = nutrientLogRepository.findById(nutrientLogId);
        if(nutLog.isPresent()){
            NutrientLog toUpdate = nutLog.get();
            toUpdate.setNutrientLogDate(updateNutLog.getNutrientLogDate());
            nutrientLogRepository.saveOneNutrientLog(updateNutLog.getMemberId(), updateNutLog.getNutrientLogDate());
            return toUpdate;
        }
        return null;
    }

    public void deleteOneNutrienLogById(Long nutrientLogId) {
        nutrientLogRepository.deleteById(nutrientLogId);

    }

    @Transactional
    public NutrientLog addNutrientsToLog(Long nutrientLogId, List<Long> nutrientIds) {
        
        Optional<NutrientLog> optionalNutrientLog = nutrientLogRepository.findById(nutrientLogId);
        
        if (optionalNutrientLog.isPresent()) {
            NutrientLog nutrientLog = optionalNutrientLog.get();
            List<Nutrient> nutrients = nutrientRepository.findAllById(nutrientIds);
            nutrientLog.getIncludedNutrients().addAll(nutrients);
            nutrientLogRepository.saveOneNutrientLog(nutrientLog.getMember().getId(), nutrientLog.getNutrientLogDate());
            
            return nutrientLog;
        } else {
            // Handle log not found case
            return null;
        }
    }
    
}
