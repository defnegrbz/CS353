package com.example.fitness.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fitness.components.User;
import com.example.fitness.repositories.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
