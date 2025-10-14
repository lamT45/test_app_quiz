package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.score;
import com.app_quiz.backskeleton.services.scoreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class scorecontroller {

    @Autowired
    private scoreservice scoreservice;

    // 1️⃣ Récupérer tous les scores
    @GetMapping
    public List<score> getAllScores() {
        return scoreservice.findAllScores();
    }

    // 2️⃣ Récupérer un score par son ID
    @GetMapping("/{id}")
    public Optional<score> getScoreById(@PathVariable Long id) {
        return scoreservice.findScoreById(id);
    }

    // 3️⃣ Créer un score
    @PostMapping
    public score createScore(@RequestBody score s) {
        return scoreservice.saveScore(s);
    }

    // 4️⃣ Mettre à jour un score
    @PutMapping("/{id}")
    public score updateScore(@PathVariable Long id, @RequestBody score s) {
        s.setId(id);  // utile pour JPA afin de mettre à jour le bon enregistrement
        return scoreservice.saveScore(s);
    }

    // 5️⃣ Supprimer un score
    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
        scoreservice.deleteScore(id);
    }

    // 6️⃣ Récupérer les scores d’un utilisateur
    @GetMapping("/user/{userId}")
    public List<score> getScoresByUser(@PathVariable Long userId) {
        return scoreservice.findScoresByUserId(userId);
    }

    // 7️⃣ Récupérer les scores d’un quiz
    @GetMapping("/quiz/{quizId}")
    public List<score> getScoresByQuiz(@PathVariable Long quizId) {
        return scoreservice.findScoresByQuizId(quizId);
    }
}
