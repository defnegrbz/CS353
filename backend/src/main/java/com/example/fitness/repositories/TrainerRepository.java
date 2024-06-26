package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fitness.components.Member;
import com.example.fitness.components.Trainer;

import jakarta.transaction.Transactional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{

    // new findAllTrainers
    @Query(value = "SELECT * FROM MemberTrainerView", nativeQuery = true)
    List<Trainer> findAllTrainers();

    @Query(value = "SELECT t.*, u.* FROM trainer t JOIN user u ON u.id = t.id", nativeQuery = true)
    List<Trainer> findTrainers();

    // new find trainer by id
    @Query(value = "SELECT u.*, t.* FROM user u JOIN trainer t ON u.id = t.id WHERE u.id = :id", nativeQuery = true)
    Trainer findTrainerById(Long id);

    @Query(value = "SELECT u.*, t.*, b.busy_dates FROM user u JOIN trainer t ON u.id = t.id JOIN trainer_busy_dates b ON t.id = b.userid WHERE u.username = ?1", nativeQuery = true)
    Optional<Trainer> findTrainerByUsername(String username);

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


    @Modifying
    @Transactional
    @Query(value = "UPDATE trainer t SET t.specialization = :specialization, t.trainer_description = :trainer_description, t.trainer_experience = :trainer_experience WHERE t.id = :id", nativeQuery = true)
    void updateTrainer(@Param("id") Long id, @Param("specialization") String specialization, @Param("trainer_description") String trainer_description, @Param("trainer_experience") Integer trainer_experience);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO trainer_busy_dates (userid, busy_dates) VALUES (:userid, :busy_dates)", nativeQuery = true)
    void addTrainerBusyDate(@Param("userid") Long userid, @Param("busy_dates") LocalDate busy_dates);

    @Modifying
    @Transactional
    @Query(value = "UPDATE trainer_busy_dates b SET b.busy_dates = :busy_dates WHERE b.userid = :userid", nativeQuery = true)
    void updateTrainerBusyDates(@Param("userid") Long userid, @Param("busy_dates") List<LocalDate> busy_dates);

    // Find busy dates by trainer ID
    @Query(value = "SELECT busy_dates FROM trainer_busy_dates WHERE userid = :userid", nativeQuery = true)
    List<LocalDate> findTrainerBusyDates(@Param("userid") Long userid);
}
