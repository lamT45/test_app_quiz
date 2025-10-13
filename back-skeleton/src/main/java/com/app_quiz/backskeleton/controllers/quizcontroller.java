package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.quiz;
import com.app_quiz.backskeleton.services.quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class quizcontroller {

    @Autowired
    private quizservice quizservice;

    @GetMapping
    public List<quiz> getAllQuizzes() {
        return quizservice.findAllQuizzes();
    }

    @GetMapping("/{id}")
    public Optional<quiz> getQuizById(@PathVariable Long id) {
        return quizservice.findQuizById(id);
    }

    @PostMapping
    public quiz createQuiz(@RequestBody quiz q) {
        return quizservice.saveQuiz(q);
    }

    @PutMapping("/{id}")
    public quiz updateQuiz(@PathVariable Long id, @RequestBody quiz q) {
        q.setId(id);
        return quizservice.saveQuiz(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizservice.deleteQuiz(id);
    }
}
