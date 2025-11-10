package com.app_quiz.backskeleton.controllers.admin;

import com.app_quiz.backskeleton.models.User;
import com.app_quiz.backskeleton.services.UserService;
import com.app_quiz.backskeleton.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;

    // ==============================
    // 🔹 GET — Tous les utilisateurs
    // ==============================
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // ==============================
    // 🔹 GET — Un utilisateur par ID
    // ==============================
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id).orElse(null);
    }

    // ==============================
    // 🔹 POST — Créer un utilisateur
    // ==============================
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ==============================
    // 🔹 PUT — Modifier un utilisateur
    // ==============================
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    // ==============================
    // 🔹 DELETE — Supprimer un utilisateur (+ ses scores)
    // ==============================
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Supprime d'abord tous les scores de cet utilisateur
        scoreService.deleteScoresByUserId(id);
        // Puis supprime l'utilisateur lui-même
        userService.deleteUser(id);
    }
}
