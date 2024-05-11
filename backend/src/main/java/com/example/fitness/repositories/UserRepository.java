package com.example.fitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fitness.components.User;

public interface UserRepository extends JpaRepository<User, Long>{

    // User findByUsername(String username);

}
