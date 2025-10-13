package com.takima.backskeleton.controllers;

import com.takima.backskeleton.models.questions;
import com.takima.backskeleton.models.quizzes;
import com.takima.backskeleton.services.MajorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("majors")
@RestController
public class QuizzeController {
    private final MajorService majorService;

    public QuizzeController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping("")
    public List<questions> findAll() {
        return majorService.findAll();
    }

    @GetMapping("/{id}/students")
    public List<quizzes> getStudentsOfMajor(@PathVariable Long id) {
        return majorService.getStudentsOfMajor(id);
    }
}
