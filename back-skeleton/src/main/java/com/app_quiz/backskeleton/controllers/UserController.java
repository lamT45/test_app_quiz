package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.user;
import com.app_quiz.backskeleton.services.userservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final userservice userService;

    public UserController(userservice userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<user> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<user> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public user createUser(@RequestBody user u) {
        return userService.saveUser(u);
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user u) {
        u.setId(id); // ✅ Assurez-vous que setId existe dans le modèle
        return userService.saveUser(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
