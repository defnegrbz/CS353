package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.repositories.MemberRepository;
import com.example.fitness.repositories.TrainerRepository;
import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class LoginService {

    @PersistenceContext
    private EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public LoginService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public Object userLogin(String username, String password) { 
        Query getMember = entityManager.createNativeQuery("SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid WHERE u.username = ? AND u.password = ?")
        .setParameter(1, username).setParameter(2, password);

        Query getTrainer = entityManager.createNativeQuery("SELECT u.*, t.*, b.busy_dates FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates b ON t.id = b.userid WHERE u.username = ? AND u.password = ?")
        .setParameter(1, username).setParameter(2, password);

        List memberList = getMember.getResultList();
        List trainerList = getTrainer.getResultList();

        if (memberList.isEmpty() && trainerList.isEmpty())  {
            throw new IllegalStateException("Username or Password is Incorrect!");
        }
        else{
            if (!memberList.isEmpty()) {
                // Optional???
                Member member = memberRepository.findMemberByUsername(username).orElseThrow(() 
                -> new IllegalStateException("A member with that username does not exist!"));
                return member;
            }
            else {
                // Optional???
                Trainer trainer = trainerRepository.findTrainerByUsername(username).orElseThrow(() 
                -> new IllegalStateException("A trainer with that username does not exist!"));
                return trainer;
            }
        }
    }

    
    
}
