package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.User;
import com.app_quiz.backskeleton.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.saveUser(u);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User u) {
        u.setId(id); // ✅ Assurez-vous que setId existe dans le modèle
        return userService.saveUser(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
