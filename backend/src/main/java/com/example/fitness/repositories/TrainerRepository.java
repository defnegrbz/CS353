package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;

import jakarta.transaction.Transactional;

public interface TrainerRepository extends JpaRepository<Trainer, Long>{

    // new findAllTrainers
    @Query(value = "SELECT u.*, t.*, b.busy_dates FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates b ON t.id = b.userid", nativeQuery = true)
    List<Trainer> findAllTrainers(); 

    // new find trainer by id
    @Query(value = "SELECT u.*, t.*, b.busy_dates FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates b ON t.id = b.userid WHERE u.id = ?1", nativeQuery = true)
    Optional<Trainer> findTrainerById(Long id);

    // ADD TRAINER QUERIES
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (full_name, username, password, gender, mail, birthdate, profile_picture) " +
                   "VALUES (:full_name, :username, :password, :gender, :mail, :birthdate, :profile_picture)", nativeQuery = true)
    void addUser(@Param("full_name") String full_name, @Param("username") String username, @Param("password") String password,
    @Param("gender") String gender, @Param("mail") String mail, @Param("birthdate") LocalDate birthdate, @Param("profile_picture") String profile_picture);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO trainer (id, trainer_experience, trainer_rating, certificate, specialization, trainer_description) " +
                    "VALUES (:id, :trainer_experience, :trainer_rating, :certificate, :specialization, :trainer_description)", nativeQuery = true)
    void addTrainer(@Param("id") Long id, @Param("trainer_experience") Integer trainer_experience, @Param("trainer_rating") Double trainer_rating, 
    @Param("certificate") String certificate, @Param("specialization") String specialization, @Param("trainer_description") String trainer_description);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO trainer_busy_dates (userid, busy_dates) " +
                    "VALUES (:userid, :busy_dates)", nativeQuery = true)
    void addTrainerBusyDates(@Param("userid") Long userid, @Param("busy_dates") List<LocalDate> busy_dates);


    @Query(value = "SELECT u.id FROM user u WHERE u.username = ?1", nativeQuery = true)
    Long getUserIdByUsername(String username);

    // DELETE TRAINER QUERIES
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM trainer_busy_dates b WHERE b.userid = ?1", nativeQuery = true)
    void deleteBusyDatesById(Long trainerId);
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM trainer t WHERE t.id = ?1", nativeQuery = true)
    void deleteTrainerById(Long trainerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user u WHERE u.id = ?1", nativeQuery = true)
    void deleteUserById(Long trainerId);

    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO user (fullname, username, password, gender, mail, birthDate, profilePicture) " +
    //                "VALUES (:fullname, :username, :password, :gender, :mail, :birthDate, :profilePicture)"+
    //                 "INSERT INTO member (trainerDescription, specialization, trainerExperience, certificate, trainerRating, busyDates) " +
    //                 "VALUES (:trainerDescription, :specialization, :trainerExperience, :certificate, :trainerRating, :busyDates",
    //                 nativeQuery = true)
    // Trainer addTrainer(Trainer newTrainer);
    // void addTrainer(@Param("fullname") String fullname, @Param("username") String username, @Param("password") String password,
    //                 @Param("gender") String gender, @Param("mail") String mail,
    //                 @Param("birthDate") LocalDate birthDate, @Param("profilePicture") String profilePicture, 
    //                 @Param("fitnessGoals") List<String> fitnessGoals, @Param("sugCalorieIntake") Integer sugCalorieIntake);

    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO user (fullname, username, password, gender, mail, birthDate, profilePicture) " +
    //             "VALUES (:fullname, :username, :password, :gender, :mail, :birthDate, :profilePicture); " + // Note the semicolon to separate the statements
    //             "INSERT INTO trainer (trainerDescription, specialization, trainerExperience, certificate, trainerRating, busyDates) " +
    //             "VALUES (:trainerDescription, :specialization, :trainerExperience, :certificate, :trainerRating, :busyDates)",
    //             nativeQuery = true)
    // Trainer addTrainer(@Param("fullname") String fullname, @Param("username") String username, @Param("password") String password, 
    // @Param("gender") String gender, @Param("mail") String mail, @Param("birthDate") LocalDate birthDate,
    // @Param("profilePicture") String profilePicture, @Param("trainerDescription") String trainerDescription, 
    // @Param("specialization") String specialization, @Param("trainerExperience") int trainerExperience, 
    // @Param("certificate") String certificate, @Param("trainerRating") double trainerRating, @Param("busyDates") List<LocalDate> busyDates);

}
