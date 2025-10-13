package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.user;
import com.app_quiz.backskeleton.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class usercontroller {

    @Autowired
    private userservice userservice;

    @GetMapping
    public List<user> getAllUsers() {
        return userservice.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<user> getUserById(@PathVariable Long id) {
        return userservice.findUserById(id);
    }

    @PostMapping
    public user createUser(@RequestBody user u) {
        return userservice.saveUser(u);
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user u) {
        u.setId(id);
        return userservice.saveUser(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userservice.deleteUser(id);
    }
}
