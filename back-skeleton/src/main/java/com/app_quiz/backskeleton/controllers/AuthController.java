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
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> userOpt = userService.findByEmail(email);
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
