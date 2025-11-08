package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.QuizDao;
import com.app_quiz.backskeleton.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizdao;

    public List<Quiz> findAllQuizzes() {
        return quizdao.findAll();
    }

    public Optional<Quiz> findQuizById(Long id) {
        return quizdao.findById(id);
    }

    public Quiz saveQuiz(Quiz q) {
        return quizdao.save(q);
    }

    public void deleteQuiz(Long id) {
        quizdao.deleteById(id);
    }
}
