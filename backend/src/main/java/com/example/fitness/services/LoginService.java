package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.fitness.repositories.MemberRepository;
import com.example.fitness.repositories.TrainerRepository;
import com.example.fitness.requests.LoginRequest;
import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginService {

     private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    @PersistenceContext
    private EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public LoginService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    // public ResponseEntity<Long> userLogin(LoginRequest request) { 
    //     Query getMember = entityManager.createNativeQuery("SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid WHERE u.username = ? AND u.password = ?")
    //     .setParameter(1, request.getUsername()).setParameter(2, request.getPassword());

    //     Query getTrainer = entityManager.createNativeQuery("SELECT u.*, t.*, b.busy_dates FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates b ON t.id = b.userid WHERE u.username = ? AND u.password = ?")
    //     .setParameter(1, request.getUsername()).setParameter(2, request.getPassword());

    //     List memberList = getMember.getResultList();
    //     List trainerList = getTrainer.getResultList();

    //     if (memberList.isEmpty() && trainerList.isEmpty())  {
    //         return new ResponseEntity(0, HttpStatus.OK);
    //     }
    //     else{
    //         if (!memberList.isEmpty()) {
    //             // Optional???
    //             Member member = memberRepository.findMemberByUsername(request.getUsername()).orElseThrow(() 
    //             -> new IllegalStateException("A member with that username does not exist!"));
    //             return new ResponseEntity(member.getId(), HttpStatus.OK);
    //         }
    //         else {
    //             // Optional???
    //             Trainer trainer = trainerRepository.findTrainerByUsername(request.getUsername()).orElseThrow(() 
    //             -> new IllegalStateException("A trainer with that username does not exist!"));
    //             return new ResponseEntity(trainer.getId(), HttpStatus.OK);
    //         }
    //     }
    // }

    public ResponseEntity<Map<String, Object>> userLogin(LoginRequest request) { 

        logger.debug("Username: {}", request.getUsername());
        logger.debug("Password: {}", request.getPassword());
        // Query to get member details
        Query getMemberQuery = entityManager.createNativeQuery(
            "SELECT u.id FROM user u JOIN member m ON u.id = m.id  WHERE u.username = ? AND u.password = ?")
            .setParameter(1, request.getUsername())
            .setParameter(2, request.getPassword());
    
        // Query to get trainer details
        Query getTrainerQuery = entityManager.createNativeQuery(
            "SELECT u.id FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates tbd ON t.id = tbd.userid WHERE u.username = ? AND u.password = ?")
            .setParameter(1, request.getUsername())
            .setParameter(2, request.getPassword());
    
        // Get results
        List<Long> memberList = getMemberQuery.getResultList();
        List<Long> trainerList = getTrainerQuery.getResultList();

        logger.debug("Member List: {}", memberList);
        logger.debug("Trainer List: {}", trainerList);

        Map<String, Object> result = new HashMap<>();

        // Check if user is a member
        if (!memberList.isEmpty()) {
            Long memberId = memberList.get(0);
            result.put("userId", memberId);
            result.put("userType", "member");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    
        // Check if user is a trainer
        if (!trainerList.isEmpty()) {
            Long trainerId = trainerList.get(0);
            result.put("userId", trainerId);
            result.put("userType", "trainer");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    
        // If user is neither a member nor a trainer
        result.put("userId", 0L);
        result.put("userType", "");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    
}
