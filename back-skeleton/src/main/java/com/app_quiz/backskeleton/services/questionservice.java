package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.questiondao;
import com.app_quiz.backskeleton.models.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class questionservice {

    @Autowired
    private questiondao questiondao;

    public List<question> findAllQuestions() {
        return questiondao.findAll();
    }

    public Optional<question> findQuestionById(Long id) {
        return questiondao.findById(id);
    }

    public question saveQuestion(question q) {
        return questiondao.save(q);
    }

    public void deleteQuestion(Long id) {
        questiondao.deleteById(id);
    }
}
