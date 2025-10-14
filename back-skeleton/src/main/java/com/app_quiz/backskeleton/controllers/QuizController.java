package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.quiz;
import com.app_quiz.backskeleton.services.quizservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final quizservice quizService;

    public QuizController(quizservice quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<quiz> getAllQuizzes() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public Optional<quiz> getQuizById(@PathVariable Long id) {
        return quizService.findQuizById(id);
    }

    @PostMapping
    public quiz createQuiz(@RequestBody quiz q) {
        return quizService.saveQuiz(q);
    }

    @PutMapping("/{id}")
    public quiz updateQuiz(@PathVariable Long id, @RequestBody quiz q) {
        q.setId(id); // ✅ Assure-toi que la méthode setId existe dans le modèle quiz
        return quizService.saveQuiz(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }
}
