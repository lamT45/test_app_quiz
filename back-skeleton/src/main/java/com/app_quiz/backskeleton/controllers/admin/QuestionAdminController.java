package com.app_quiz.backskeleton.controllers.admin;

import com.app_quiz.backskeleton.models.Question;
import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.services.QuestionService;
import com.app_quiz.backskeleton.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/questions")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionAdminController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

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
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        try {
            if (question.getQuiz() != null && question.getQuiz().getId() != null) {
                Quiz quiz = quizService.findQuizById(question.getQuiz().getId()).orElse(null);
                question.setQuiz(quiz);
            } else {
                System.out.println("⚠️ Aucun quiz associé à la question !");
                question.setQuiz(null);
            }

            Question saved = questionService.saveQuestion(question);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==============================
    // 🔹 PUT — Modifier une question
    // ==============================
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        try {
            System.out.println("🟣 Requête PUT reçue pour la question ID=" + id);
            System.out.println("🟣 Données reçues : " + question);

            question.setId(id);

            if (question.getQuiz() != null && question.getQuiz().getId() != null) {
                System.out.println("🟢 Quiz lié : " + question.getQuiz().getId());
            } else {
                System.out.println("⚠️ Aucun quiz associé à la question !");
            }

            Question updated = questionService.saveQuestion(question);

            System.out.println("✅ Question mise à jour : " + updated);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==============================
    // 🔹 DELETE — Supprimer une question
    // ==============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
