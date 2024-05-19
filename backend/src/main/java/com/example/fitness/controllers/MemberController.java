package com.example.fitness.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.components.Member;
import com.example.fitness.components.User;
import com.example.fitness.services.UserService;

import jakarta.validation.Payload;

@RestController
@RequestMapping("/members")
public class MemberController{
     
    private UserService userService;

    public MemberController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return userService.getAllMembers();
    }

    @PostMapping
    public void createMember(@RequestBody Member newMember){
        //userService.saveOneMember(newMember);
    }

    @GetMapping("/{memberId}") 
    public User getOneMember(@PathVariable Long memberId){
        return userService.getMember(memberId);
    }

    @PutMapping("/update/{memberId}")
    public void updateOneMember(@PathVariable Long memberId, @RequestBody Member newMember){
        //userService.updateOneMember(memberId, newMember);
    }

    @DeleteMapping("/delete/{memberId}")
    public void deleteOneMember(@PathVariable Long memberId){
        userService.deleteByIdMember((memberId));
    }

}
