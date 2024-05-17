package com.example.fitness.services;

import java.time.LocalDate;
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

@Service
public class UserService {

    UserRepository userRepository;
    MemberRepository memberRepository;
    TrainerRepository trainerRepository;

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

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
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
            foundUser.setProfilePicture(newUser.getProfilePicture());
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

    public Member getOneMember(Long memberId) {
        return memberRepository.findMemberById(memberId).orElse(null);
    }

    // public void saveOneMember(Map<String, Object> payload) {
    //     // Extracting payload values
    //     String fullname = (String) payload.get("fullname");
    //     String username = (String) payload.get("username");
    //     String password = (String) payload.get("password");
    //     String gender = (String) payload.get("gender");
    //     String mail = (String) payload.get("mail");
    //     LocalDate birthDate = (LocalDate) payload.get("birthDate");
    //     String profilePicture = (String) payload.get("profilePicture");
    //     List<String> fitnessGoals = (List<String>) payload.get("fitnessGoals");
    //     Integer sugCalorieIntake = (Integer) payload.get("sugCalorieIntake");


    //     // Call the repository method to add the workout
    //     memberRepository.addMember(fullname, username, password, gender, mail, birthDate, profilePicture, fitnessGoals, sugCalorieIntake);
    // }

    // public Member saveOneMember(Member newMember) {
    //     return memberRepository.addMember(newMember.getFullName(), newMember.getUsername(), newMember.getPassword(), 
    //     newMember.getGender(), newMember.getMail(), newMember.getBirthdate(), newMember.getProfilePicture(), 
    //     newMember.getFitnessGoals(), newMember.getSugCalorieIntake());
    // }

    public void saveOneMember(Member newMember) {
        memberRepository.addUser(newMember.getFullName(), newMember.getUsername(), newMember.getPassword(), 
        newMember.getGender(), newMember.getMail(), newMember.getBirthdate(), newMember.getProfilePicture());

        Long insertedMemberId = memberRepository.getUserIdByUsername(newMember.getUsername());
        memberRepository.addMember(insertedMemberId, newMember.getSugCalorieIntake());
        memberRepository.addMemberFitnessGoals(insertedMemberId, newMember.getFitnessGoals());
    }

    public ResponseEntity<Long> registerMember(RegisterRequest request) {
        memberRepository.addUser(request.getFullName(), request.getUsername(), request.getPassword(), 
        request.getGender(), request.getMail(), request.getBirthdate(), request.getProfilePicture());

        Long insertedMemberId = memberRepository.getUserIdByUsername(request.getUsername());
        Integer sugCalorieIntake = 1000;
        memberRepository.addMember(insertedMemberId, sugCalorieIntake);
        memberRepository.addMemberFitnessGoals(insertedMemberId, request.getFitnessGoals());
        return new ResponseEntity<> (insertedMemberId, HttpStatus.OK);
    }

    // public Member saveOneMember(Member newMember) {
    //     return memberRepository.addMember(newMember.getId(), newMember.getId(), newMember.getFullName(), newMember.getUsername(), newMember.getPassword(), 
    //     newMember.getGender(), newMember.getMail(), newMember.getBirthdate(), newMember.getProfilePicture(), 
    //     newMember.getFitnessGoals(), newMember.getSugCalorieIntake());
    // }

    // public Member saveOneMember(Member newMember) {
    //     // Extract necessary fields from the newMember object
    //     String fullname = newMember.getFullName();
    //     String username = newMember.getUsername();
    //     String password = newMember.getPassword();
    //     String gender = newMember.getGender();
    //     String mail = newMember.getMail();
    //     LocalDate birthDate = newMember.getBirthdate();
    //     String profilePicture = newMember.getProfilePicture();
    //     List<String> fitnessGoals = newMember.getFitnessGoals();
    //     Integer sugCalorieIntake = newMember.getSugCalorieIntake();
        
    //     // Call the repository method with extracted fields
    //     return memberRepository.addMember(fullname, username, password, gender, mail, birthDate, profilePicture, fitnessGoals, sugCalorieIntake);
    // }
    
    public void deleteByIdMember(Long memberId) {
        memberRepository.deleteFitnessgoalsById(memberId);
        memberRepository.deleteMemberById(memberId);
        memberRepository.deleteUserById(memberId);

    }
   

    // public Member updateOneMember(Long memberId, Member newMember) {
    //     Optional<Member> member = memberRepository.updateMember(memberId);
    //     if(member.isPresent()){
    //         Member foundMember = member.get();
    //         foundMember.setUsername(newMember.getUsername());
    //         foundMember.setPassword(newMember.getPassword());
    //         foundMember.setFullName(newMember.getFullName());
    //         foundMember.setBirthdate(newMember.getBirthdate());
    //         foundMember.setMail(newMember.getMail());
    //         foundMember.setProfilePicture(newMember.getProfilePicture());
    //         foundMember.setGender(newMember.getGender());
    //         userRepository.save(foundMember);
    //         return foundMember;
    //     }
    //     else
    //         {return null;}
    // }

    public void updateOneMember(Long memberId, Member newMember) {
        memberRepository.updateUser(memberId, newMember.getFullName(), newMember.getUsername(), newMember.getPassword(),
        newMember.getGender(), newMember.getMail(), newMember.getBirthdate(), newMember.getProfilePicture());
        memberRepository.updateMember(memberId, newMember.getFitnessGoals());
        
    }


    // TRAINER SERVICE
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAllTrainers();
    }

    public Trainer getOneTrainer(Long trainerId) {
        return trainerRepository.findTrainerById(trainerId).orElse(null);
    }

    public void saveOneTrainer(Trainer newTrainer) {
        trainerRepository.addUser(newTrainer.getFullName(), newTrainer.getUsername(), newTrainer.getPassword(), 
        newTrainer.getGender(), newTrainer.getMail(), newTrainer.getBirthdate(), newTrainer.getProfilePicture());

        Long insertedTrainerId = trainerRepository.getUserIdByUsername(newTrainer.getUsername());
        trainerRepository.addTrainer(insertedTrainerId, newTrainer.getTrainerExperience(), newTrainer.getTrainerRating(), newTrainer.getCertificate(), 
        newTrainer.getSpecialization(), newTrainer.getTrainerDescription());
        trainerRepository.addTrainerBusyDates(insertedTrainerId, newTrainer.getBusyDates());
    }

    // public Trainer updateOneTrainer(Long trainerId, Trainer newTrainer) {
    //     Optional<Trainer> trainer = trainerRepository.findById(trainerId);
    //     if(trainer.isPresent()){
    //         Trainer foundTrainer = trainer.get();
    //         foundTrainer.setUsername(newTrainer.getUsername());
    //         foundTrainer.setPassword(newTrainer.getPassword());
    //         foundTrainer.setFullName(newTrainer.getFullName());
    //         foundTrainer.setBirthdate(newTrainer.getBirthdate());
    //         foundTrainer.setMail(newTrainer.getMail());
    //         foundTrainer.setProfilePicture(newTrainer.getProfilePicture());
    //         foundTrainer.setGender(newTrainer.getGender());
    //         userRepository.save(foundTrainer);
    //         return foundTrainer;
    //     }
    //     else
    //         {return null;}
    // }

    public void updateOneTrainer(Long trainerId, Trainer newTrainer) {
        memberRepository.updateUser(trainerId, newTrainer.getFullName(), newTrainer.getUsername(), newTrainer.getPassword(),
        newTrainer.getGender(), newTrainer.getMail(), newTrainer.getBirthdate(), newTrainer.getProfilePicture());
        trainerRepository.updateTrainer(trainerId, newTrainer.getSpecialization(), newTrainer.getTrainerDescription(), newTrainer.getTrainerExperience());
        trainerRepository.updateTrainerBusyDates(trainerId, newTrainer.getBusyDates());
    }

    public void deleteByIdTrainer(Long trainerId) {
        trainerRepository.deleteBusyDatesById(trainerId);
        trainerRepository.deleteTrainerById(trainerId);
        trainerRepository.deleteUserById(trainerId);

    }




}
