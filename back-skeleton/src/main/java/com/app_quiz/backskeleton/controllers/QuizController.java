package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.services.QuestionService;
import com.app_quiz.backskeleton.services.QuizService;
import org.springframework.web.bind.annotation.*;
import com.app_quiz.backskeleton.DTO.QuizDto;
import org.springframework.http.ResponseEntity;
import com.app_quiz.backskeleton.models.Question;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quizOpt = quizService.findQuizById(id);
        if (quizOpt.isEmpty()) return ResponseEntity.notFound().build();

        Quiz quiz = quizOpt.get();
        QuizDto dto = new QuizDto();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setCategory(quiz.getCategory());
        dto.setDescription(quiz.getDescription());
        dto.setLevel(quiz.getLevel());
        dto.setPlayers(quiz.getPlayers());
        dto.setDuration(quiz.getDuration());
        dto.setCreatedById(quiz.getCreatedBy() != null ? quiz.getCreatedBy().getId() : null);
        dto.setQuestionIds(
                quiz.getQuestions() != null
                        ? quiz.getQuestions().stream().map(q -> q.getId()).toList()
                        : null
        );

        return ResponseEntity.ok(dto);
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

    @GetMapping("/{id}/questions")
    public List<Question> getQuestionsByQuizId(@PathVariable Long id) {
        return questionService.findByQuizId(id);
    }
}

