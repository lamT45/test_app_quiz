package com.app_quiz.backskeleton.controllers.admin;

import com.app_quiz.backskeleton.models.Question;
import com.app_quiz.backskeleton.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/questions")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionAdminController {

    @Autowired
    private QuestionService questionService;

    // ==============================
    // 🔹 GET — Toutes les questions
    // ==============================
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    // ==============================
    // 🔹 GET — Questions d’un quiz
    // ==============================
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return questionService.findByQuizId(quizId);
    }

    // ==============================
    // 🔹 POST — Créer une question
    // ==============================
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    // ==============================
    // 🔹 PUT — Modifier une question
    // ==============================
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setId(id);
        return questionService.saveQuestion(question);
    }

    // ==============================
    // 🔹 DELETE — Supprimer une question
    // ==============================
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
