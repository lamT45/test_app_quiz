package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.Score;
import com.app_quiz.backskeleton.models.User;
import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.services.ScoreService;
import com.app_quiz.backskeleton.services.UserService;
import com.app_quiz.backskeleton.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.*;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;
    private final UserService userService;
    private final QuizService quizService;

    @Autowired
    public ScoreController(ScoreService scoreService, UserService userService, QuizService quizService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.quizService = quizService;
    }

    // 1️⃣ Récupérer tous les scores
    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        return ResponseEntity.ok(scoreService.findAllScores());
    }

    // 2️⃣ Récupérer un score par ID
    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        return scoreService.findScoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ Créer un score simple
    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score s) {
        try {
            Score saved = scoreService.saveScore(s);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // 4️⃣ Mettre à jour un score
    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable Long id, @RequestBody Score s) {
        try {
            s.setId(id);
            return ResponseEntity.ok(scoreService.saveScore(s));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // 5️⃣ Supprimer un score
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        try {
            scoreService.deleteScore(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 6️⃣ Récupérer les scores d’un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Score>> getScoresByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(scoreService.findScoresByUserId(userId));
    }

    // 7️⃣ Récupérer les scores d’un quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Score>> getScoresByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(scoreService.findScoresByQuizId(quizId));
    }

    // 8️⃣ Calcul automatique du score (simulation d’une fin de quiz)
    @PostMapping("/calculate")
    public ResponseEntity<Score> calculateScore(
            @RequestParam Long userId,
            @RequestParam Long quizId,
            @RequestBody Map<String, Object> payload
    ) {
        try {
            User user = userService.findUserById(userId).orElseThrow();
            Quiz quiz = quizService.findQuizById(quizId).orElseThrow();

            ObjectMapper mapper = new ObjectMapper();
            List<String> answers = mapper.convertValue(payload.get("answers"), new TypeReference<List<String>>() {});
            List<Integer> times = mapper.convertValue(payload.get("times"), new TypeReference<List<Integer>>() {});

            Score score = scoreService.calculateAndSaveScore(user, quiz, answers, times);
            return ResponseEntity.ok(score);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // 9️⃣ Leaderboard global (TOP 3 + autres)
    @GetMapping("/leaderboard")
    public ResponseEntity<Map<String, Object>> getLeaderboard() {
        List<Score> allScores = scoreService.findAllScores();

        Map<String, Integer> totalScores = new HashMap<>();
        for (Score s : allScores) {
            if (s.getUser() != null) {
                String username = s.getUser().getUsername();
                totalScores.put(username, totalScores.getOrDefault(username, 0) + s.getScore_obtained());
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(totalScores.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        List<Map<String, Object>> top = new ArrayList<>();
        List<Map<String, Object>> others = new ArrayList<>();

        for (int i = 0; i < sorted.size(); i++) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("username", sorted.get(i).getKey());
            entry.put("score", sorted.get(i).getValue());
            if (i < 3) top.add(entry);
            else others.add(entry);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("top", top);
        result.put("others", others);

        return ResponseEntity.ok(result);
    }
}
