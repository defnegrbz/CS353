package com.example.fitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fitness.components.Member;
import com.example.fitness.components.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT m, u FROM member m JOIN user u ON m.id = u.id WHERE m.id = :memberId", nativeQuery = true)
        Member findMemberWithUserById(@Param("memberId") Long memberId);
}



