package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public Optional<Quiz> getQuizById(@PathVariable Long id) {
        return quizService.findQuizById(id);
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz q) {
        return quizService.saveQuiz(q);
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz q) {
        q.setId(id); // ✅ Assure-toi que la méthode setId existe dans le modèle quiz
        return quizService.saveQuiz(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }
}
