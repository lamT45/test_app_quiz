package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.User;
import com.app_quiz.backskeleton.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("{\"message\":\"Utilisateur non trouvé\"}");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("{\"message\":\"Mot de passe incorrect\"}");
        }

        // Pas de vrai token JWT ici, juste une simulation
        return ResponseEntity.ok(
                new AuthResponse("fake-jwt-token", user)
        );
    }

    // ✅ REGISTER (corrigé)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Vérifie si l'email existe déjà
            Optional<User> existingUser = userService.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(400).body("{\"message\":\"Email déjà utilisé\"}");
            }

            // Enregistre l'utilisateur
            userService.saveUser(user);
            return ResponseEntity.ok("{\"message\":\"Utilisateur enregistré avec succès\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("{\"message\":\"Erreur lors de l'inscription : " + e.getMessage() + "\"}");
        }
    }

    // ✅ Classe interne simple pour la réponse
    static class AuthResponse {
        public String token;
        public User user;
        public AuthResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }
    }
}
