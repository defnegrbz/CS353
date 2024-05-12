package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.NutrientLog;
import com.example.fitness.components.User;
import com.example.fitness.repositories.NutrientLogRepository;
import com.example.fitness.requests.NutrientLogCreateRequest;
import com.example.fitness.requests.NutrientLogUpdateRequest;

@Service
public class NutrientLogService {
    private NutrientLogRepository nutrientLogRepository;
    private UserService memberService;

    public NutrientLogService(NutrientLogRepository nutrientLogRepository, UserService memberService) {
        this.nutrientLogRepository = nutrientLogRepository;
        this.memberService = memberService;
    }

    // public List<NutrientLog> getAllNutrientLogs(Optional<Long> member_id) {
    //     if(member_id.isPresent()){
    //         return nutrientLogRepository.findByMemberId(member_id);
    //     }
    //     return nutrientLogRepository.findAll();
    // }

    public NutrientLog getOneNutrientLogById(Long nutrientLogId) {
        return nutrientLogRepository.findById(nutrientLogId).orElse(null);
    }

    public NutrientLog createOneNutrientLog(NutrientLogCreateRequest newNutrientLogRequest) {
        User member = memberService.getOneMember(newNutrientLogRequest.getMemberId());
        
        if(member == null){
            return null;
        }

        // NutrientLog toSave = new NutrientLog();
        // toSave.setNutrientLogId(newNutrientLogRequest.getNutrientLogId());
        // toSave.setMember(newNutrientLogRequest.getMember());
        // toSave.setNutrientLogDate(newNutrientLogRequest.getNutrientLogDate());
        // toSave.setNutrientLogType(newNutrientLogRequest.getNutrientLogType());
    
        
        return nutrientLogRepository.saveOneNutrientLog(newNutrientLogRequest.getMemberId(), newNutrientLogRequest.getNutrientLogDate(), newNutrientLogRequest.getNutrientLogType()); 
    }

    public NutrientLog updateOneNutrientLogById(Long nutrientLogId, NutrientLogUpdateRequest updateNutLog) {
        Optional<NutrientLog> nutLog = nutrientLogRepository.findById(nutrientLogId);
        if(nutLog.isPresent()){
            NutrientLog toUpdate = nutLog.get();
            //toUpdate.setMember(updateNutLog.getMember());
            //toUpdate.setNutrientLogDate(updateNutLog.getNutrientLogDate());
            toUpdate.setNutrientLogType(updateNutLog.getNutrientLogType());
            toUpdate.setNutrientLogId(updateNutLog.getNutrientLogId()); 
            nutrientLogRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneNutrienLogById(Long nutrientLogId) {
        nutrientLogRepository.deleteById(nutrientLogId);

    }

    
}
