package com.example.fitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fitness.components.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT m, u FROM Member m JOIN User u ON m.id = u.id WHERE m.id = :memberId")
    User findMemberWithUserById(@Param("memberId") Long memberId);
}
