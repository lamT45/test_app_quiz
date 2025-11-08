package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.Question;
import com.app_quiz.backskeleton.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/{id}")
    public Optional<Question> getQuestionById(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question q) {
        return questionService.saveQuestion(q);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question q) {
        q.setId(id); // ✅ Assurez-vous que setId existe dans le modèle
        return questionService.saveQuestion(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
