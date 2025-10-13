package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.question;
import com.app_quiz.backskeleton.services.questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class questioncontroller {

    @Autowired
    private questionservice questionservice;

    @GetMapping
    public List<question> getAllQuestions() {
        return questionservice.findAllQuestions();
    }

    @GetMapping("/{id}")
    public Optional<question> getQuestionById(@PathVariable Long id) {
        return questionservice.findQuestionById(id);
    }

    @PostMapping
    public question createQuestion(@RequestBody question q) {
        return questionservice.saveQuestion(q);
    }

    @PutMapping("/{id}")
    public question updateQuestion(@PathVariable Long id, @RequestBody question q) {
        q.setId(id);
        return questionservice.saveQuestion(q);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionservice.deleteQuestion(id);
    }
}
