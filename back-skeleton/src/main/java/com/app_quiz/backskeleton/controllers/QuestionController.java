package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.question;
import com.app_quiz.backskeleton.services.questionservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final questionservice questionService;

    public QuestionController(questionservice questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/{id}")
    public Optional<question> getQuestionById(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @PostMapping
    public question createQuestion(@RequestBody question q) {
        return questionService.saveQuestion(q);
    }

    @PutMapping("/{id}")
    public question updateQuestion(@PathVariable Long id, @RequestBody question q) {
        q.setId(id); // ✅ Assurez-vous que setId existe dans le modèle
        return questionService.saveQuestion(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
