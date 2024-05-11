package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.HealthData;
import com.example.fitness.repositories.HealthDataRepository;

@Service
public class HealthDataService {

    HealthDataRepository healthDataRepository;

    
    public HealthDataService(HealthDataRepository healthDataRepository) {
        this.healthDataRepository = healthDataRepository;
    }

    public HealthData saveOneHealthData(HealthData newHealthData) {
        return healthDataRepository.save(newHealthData);
    }

    public HealthData getOneHealthData(Long userId) {
        return healthDataRepository.findById(userId).orElse(null);
    }

    // public List<HealthData> getHealthDatas(Optional<Long> userId) {
    //     if(userId.isPresent()){
    //         return healthDataRepository.findByUserId(userId.get());
    //     }
    //     return healthDataRepository.findAll();
    // }

    public HealthData updateOneHealthData(Long userId, HealthData newHealthData) {
        Optional<HealthData> healthData = healthDataRepository.findById(userId);
        if(healthData.isPresent()){
            HealthData foundHealthData = healthData.get();
            foundHealthData.setHeight(newHealthData.getHeight());
            foundHealthData.setWeight(newHealthData.getWeight());
            foundHealthData.setAllergies(newHealthData.getAllergies());
            foundHealthData.setDiseases(newHealthData.getDiseases());
            foundHealthData.setMedications(newHealthData.getMedications());
            foundHealthData.setUser(newHealthData.getUser());
            healthDataRepository.save(foundHealthData);
            return foundHealthData;
        }
        else
            {return null;}
    }

    public void deleteById(Long userId) {
        healthDataRepository.deleteById(userId);
    }



}
