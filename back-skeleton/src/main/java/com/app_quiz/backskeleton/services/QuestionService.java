package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.QuestionDao;
import com.app_quiz.backskeleton.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questiondao;

    public List<Question> findAllQuestions() {
        return questiondao.findAll();
    }

    public Optional<Question> findQuestionById(Long id) {
        return questiondao.findById(id);
    }

    public Question saveQuestion(Question q) {
        return questiondao.save(q);
    }

    public void deleteQuestion(Long id) {
        questiondao.deleteById(id);
    }
}
