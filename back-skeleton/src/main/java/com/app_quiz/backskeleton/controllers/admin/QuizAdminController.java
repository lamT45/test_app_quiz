package com.app_quiz.backskeleton.controllers.admin;

import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.services.QuizService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.app_quiz.backskeleton.models.Question;


@RestController
@RequestMapping("/api/admin/quizzes")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizAdminController {

    @Autowired
    private QuizService quizService;

    // 🔹 GET — Tous les quiz
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.findAllQuizzes();
    }

    // 🔹 POST — Créer un quiz
    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        if (quiz.getLevel() != null) {
            switch (quiz.getLevel().toLowerCase()) {
                case "facile": quiz.setDuration(35); break;
                case "moyen": quiz.setDuration(25); break;
                case "difficile": quiz.setDuration(15); break;
                default: quiz.setDuration(15);
            }
        } else {
            quiz.setDuration(15);
        }
        return quizService.saveQuiz(quiz);
    }

    // 🔹 PUT — Modifier un quiz
    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        quiz.setId(id);
        return quizService.saveQuiz(quiz);
    }

    // 🔹 DELETE — Supprimer un quiz
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/{quizId}/questions")
    public List<Question> getQuestionsForQuiz(@PathVariable Long quizId) {
        return quizService.findByQuizId(quizId);
    }

}
