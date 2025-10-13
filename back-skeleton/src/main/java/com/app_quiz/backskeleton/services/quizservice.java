package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.quizdao;
import com.app_quiz.backskeleton.models.quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class quizservice {

    @Autowired
    private quizdao quizdao;

    public List<quiz> findAllQuizzes() {
        return quizdao.findAll();
    }

    public Optional<quiz> findQuizById(Long id) {
        return quizdao.findById(id);
    }

    public quiz saveQuiz(quiz q) {
        return quizdao.save(q);
    }

    public void deleteQuiz(Long id) {
        quizdao.deleteById(id);
    }
}
