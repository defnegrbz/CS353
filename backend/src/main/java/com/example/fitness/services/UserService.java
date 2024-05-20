package com.example.fitness.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;
import com.example.fitness.components.User;
import com.example.fitness.repositories.MemberRepository;
import com.example.fitness.repositories.TrainerRepository;
import com.example.fitness.repositories.UserRepository;
import com.example.fitness.requests.RegisterRequest;
import com.example.fitness.requests.RegisterTrainerRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    UserRepository userRepository;
    MemberRepository memberRepository;
    TrainerRepository trainerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
        this.memberRepository = memberRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public Member getMember(Long userId) {
        return memberRepository.findMemberById(userId);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setFullName(newUser.getFullName());
            foundUser.setBirthdate(newUser.getBirthdate());
            foundUser.setMail(newUser.getMail());
            foundUser.setGender(newUser.getGender());
            userRepository.save(foundUser);
            return foundUser;
        }
        else
            {return null;}
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    // MEMBER SERVICE
    public List<Member> getAllMembers() {
        return memberRepository.findAllMembers();
    }

    @Transactional
    public Member registerMember(RegisterRequest request) {

            String fullName = request.getFullName();
            String username = request.getUsername();
            String password = request.getPassword();
            String gender = request.getGender();
            String mail = request.getMail();
            LocalDate birthdate = request.getBirthdate();
            Integer height = request.getHeight();
            Double weight = request.getWeight();
            String allergies = request.getAllergies();
            String diseases = request.getDiseases();
            String medications = request.getMedications();
            String fitnessGoals = request.getFitnessGoals();

        try {
            // Execute native SQL query to insert user data
            entityManager.createNativeQuery("INSERT INTO user (full_name, username, password, gender, mail, birthdate) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")
                    .setParameter(1, fullName)
                    .setParameter(2, username)
                    .setParameter(3, password)
                    .setParameter(4, gender)
                    .setParameter(5, mail)
                    .setParameter(6, birthdate)
                    .executeUpdate();

            // Execute native SQL query to retrieve the ID of the inserted user
            Long userId = (Long) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();

            // Execute native SQL query to insert member data
            entityManager.createNativeQuery("INSERT INTO member (id, sug_calorie_intake, height, weight, allergies, diseases, medications, fitness_goals) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                    .setParameter(1, userId.longValue())
                    .setParameter(2, calculateSugCalorieIntake(weight, height))
                    .setParameter(3, height)
                    .setParameter(4, weight)
                    .setParameter(5, allergies)
                    .setParameter(6, diseases)
                    .setParameter(7, medications)
                    .setParameter(8, fitnessGoals)
                    .executeUpdate();
        } catch (Exception e) {
            // Handle exception
        }
        // Return null or handle return value as needed
        return null;
    }

    @Transactional
    public Trainer registerTrainer(RegisterTrainerRequest request) {
        String fullName = request.getFullName();
        String username = request.getUsername();
        String password = request.getPassword();
        String gender = request.getGender();
        String mail = request.getMail();
        LocalDate birthdate = request.getBirthdate();
        String trainerDescription = request.getDescription();
        String specialization = request.getSpecialization();
        Integer trainerExperience = request.getExperience();
        String certificate = request.getCertificate();
        Double trainerRating = 0.0;
        Integer voteScore = 0;
        Integer voteCount = 0;

        try {
            List<LocalDate> busyDates = new ArrayList<>();
            // Execute native SQL query to insert user data
            entityManager.createNativeQuery("INSERT INTO user (full_name, username, password, gender, mail, birthdate) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")
                    .setParameter(1, fullName)
                    .setParameter(2, username)
                    .setParameter(3, password)
                    .setParameter(4, gender)
                    .setParameter(5, mail)
                    .setParameter(6, birthdate)
                    .executeUpdate();

            // Execute native SQL query to retrieve the ID of the inserted user
            Long userId = (Long) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();

            // Execute native SQL query to insert trainer data
            entityManager.createNativeQuery("INSERT INTO trainer (id, trainer_description, specialization, trainer_experience, certificate, trainer_rating, vote_score, vote_count) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                    .setParameter(1, userId)
                    .setParameter(2, trainerDescription)
                    .setParameter(3, specialization)
                    .setParameter(4, trainerExperience)
                    .setParameter(5, certificate)
                    .setParameter(6, trainerRating)
                    .setParameter(7, voteScore)
                    .setParameter(8, voteCount)
                    .executeUpdate();

            entityManager.createNativeQuery("INSERT INTO trainer_busy_dates (userid, busy_dates) " +
                    "VALUES (?, ?)")
                    .setParameter(1, userId)
                    .setParameter(2, busyDates)
                    .executeUpdate();

        } catch (Exception e) {
            // Handle exception
        }
        // Return null or handle return value as needed
        return null;
    }


    // Method to calculate suggested calorie intake 
    private Integer calculateSugCalorieIntake(Double weight, Integer height) {
        // Your calculation logic here
        return (int) (weight * height); 
    }


    
    public void deleteByIdMember(Long memberId) {
        memberRepository.deleteFitnessgoalsById(memberId);
        memberRepository.deleteMemberById(memberId);
        memberRepository.deleteUserById(memberId);

    }


    // TRAINER SERVICE
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findTrainers();
    }

    public Trainer getOneTrainer(Long trainerId) {
        return trainerRepository.findTrainerById(trainerId);
    }

    public List<LocalDate> getBusyDates(Long trainerID) {
        return trainerRepository.findTrainerBusyDates(trainerID);
    }

    public void deleteByIdTrainer(Long trainerId) {
        trainerRepository.deleteBusyDatesById(trainerId);
        trainerRepository.deleteTrainerById(trainerId);
        trainerRepository.deleteUserById(trainerId);

    }

    public String addBusyDate(Long trainerID, LocalDate date) {
        Trainer trainer = trainerRepository.findById(trainerID).orElse(null);
        if (trainer != null) {
            trainerRepository.addTrainerBusyDate(trainerID, date);
            return trainer.getMail();
        } else {
            return null;
        }
    }
    public void voteTrainer(Long trainerId, Integer vote) {
        Trainer trainer = trainerRepository.findTrainerById(trainerId);
        if(trainer != null) {
            if (trainer.getTrainerRating() == null) {
                trainer.setTrainerRating(0.0);
                
            } 
            trainer.setVoteScore(vote);
            trainer.setVoteCount(trainer.getVoteCount() + 1);  
            //ensure the result is point number with 1 decimal
            trainer.setTrainerRating((double) Math.round((trainer.getVoteScore() / trainer.getVoteCount()) * 10) / 10);
        }
    }







}
