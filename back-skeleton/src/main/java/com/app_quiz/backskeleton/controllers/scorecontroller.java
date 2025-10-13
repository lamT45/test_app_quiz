package com.app_quiz.backskeleton.controllers;

import com.app_quiz.backskeleton.models.score;
import com.app_quiz.backskeleton.services.scoreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class scorecontroller {

    @Autowired
    private scoreservice scoreservice;

    @GetMapping
    public List<score> getAllScores() {
        return scoreservice.findAllScores();
    }

    @GetMapping("/{id}")
    public Optional<score> getScoreById(@PathVariable Long id) {
        return scoreservice.findScoreById(id);
    }

    @PostMapping
    public score createScore(@RequestBody score s) {
        return scoreservice.saveScore(s);
    }

    @PutMapping("/{id}")
    public score updateScore(@PathVariable Long id, @RequestBody score s) {
        s.setId(id);
        return scoreservice.saveScore(s);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Long id) {
