package com.example.fitness.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.fitness.components.Member;


import jakarta.transaction.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    // new findAllMembers
    @Query(value = "SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid", nativeQuery = true)
    //@Query(value = "SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u, member m, member_fitness_goals f WHERE u.id = m.id AND m.id = f.userid", nativeQuery = true)
    List<Member> findAllMembers(); 

    //@Query(value = "SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid", nativeQuery = true)
    @Query(value = "SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid WHERE u.id = ?1", nativeQuery = true)
    Optional<Member> findMemberById(Long id);

    @Query(value = "SELECT u.*, m.sug_calorie_intake, f.fitness_goals FROM user u JOIN member m ON u.id = m.id JOIN member_fitness_goals f ON m.id = f.userid WHERE u.username = ?1", nativeQuery = true)
    Optional<Member> findMemberByUsername(String username);

    // ADD MEMBER QUERIES
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (full_name, username, password, gender, mail, birthdate, profile_picture) " +
                   "VALUES (:full_name, :username, :password, :gender, :mail, :birthdate, :profile_picture)", nativeQuery = true)
    void addUser(@Param("full_name") String full_name, @Param("username") String username, @Param("password") String password,
    @Param("gender") String gender, @Param("mail") String mail, @Param("birthdate") LocalDate birthdate, @Param("profile_picture") String profile_picture);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO member (id, sug_calorie_intake) " +
                    "VALUES (:id, :sug_calorie_intake)", nativeQuery = true)
    void addMember(@Param("id") Long id, @Param("sug_calorie_intake") Integer sug_calorie_intake);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO member_fitness_goals (userid, fitness_goals) " +
                    "VALUES (:userid, :fitness_goals)", nativeQuery = true)
    void addMemberFitnessGoals(@Param("userid") Long userid, @Param("fitness_goals") List<String> fitness_goals);


    @Query(value = "SELECT u.id FROM user u WHERE u.username = ?1", nativeQuery = true)
    Long getUserIdByUsername(String username);

    // DELETE MEMBER QUERY
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM member_fitness_goals f WHERE f.userid = ?1", nativeQuery = true)
    void deleteFitnessgoalsById(Long memberId);

    @Modifying
    @Transactional
    //@Query(value = "DELETE FROM member m, user u, member_fitness_goals f WHERE m.id = ?1", nativeQuery = true)
    //@Query(value = "DELETE f, m, u FROM member m JOIN user u ON m.id = u.id JOIN member_fitness_goals f ON m.id = f.userid WHERE m.id = ?1", nativeQuery = true)
    @Query(value = "DELETE FROM member m WHERE m.id = ?1", nativeQuery = true)
    void deleteMemberById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user u WHERE u.id = ?1", nativeQuery = true)
    void deleteUserById(Long memberId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE user u SET u.full_name = :full_name, u.username = :username, u.password = :password, u.gender = :gender, u.mail = :mail, u.birthdate = :birthdate, u.profile_picture = :profile_picture WHERE u.id = :id", nativeQuery = true)
    void updateUser(@Param("id") Long id, @Param("full_name") String full_name, @Param("username") String username, @Param("password") String password,
                    @Param("gender") String gender, @Param("mail") String mail, @Param("birthdate") LocalDate birthdate, @Param("profile_picture") String profile_picture);

    @Modifying
    @Transactional
    @Query(value = "UPDATE member_fitness_goals f SET f.fitness_goals = :fitness_goals WHERE f.userid = :userid", nativeQuery = true)
    void updateMember(@Param("userid") Long userid, @Param("fitness_goals") List<String> fitness_goals);

           
}
