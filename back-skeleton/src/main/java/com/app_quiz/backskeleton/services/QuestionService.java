package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.QuestionDao;
import com.app_quiz.backskeleton.DAO.QuizDao;
import com.app_quiz.backskeleton.models.Question;
import com.app_quiz.backskeleton.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questiondao;

    @Autowired
    private QuizDao quizdao;

    //  Trouver toutes les questions
    public List<Question> findAllQuestions() {
        return questiondao.findAll();
    }

    //  Trouver une question par ID
    public Optional<Question> findQuestionById(Long id) {
        return questiondao.findById(id);
    }

    //  Ajouter / Mettre à jour une question
    public Question saveQuestion(Question q) {
        // 🟣 Si la question contient un quiz avec un ID, on recharge le quiz depuis la base
        if (q.getQuiz() != null && q.getQuiz().getId() != null) {
            Optional<Quiz> existingQuiz = quizdao.findById(q.getQuiz().getId());
            existingQuiz.ifPresent(q::setQuiz);
        } else {
            q.setQuiz(null);
        }

        // 🟢 Sauvegarde sécurisée
        return questiondao.save(q);
    }

    //  Supprimer une question
    public void deleteQuestion(Long id) {
        questiondao.deleteById(id);
    }

    //  Récupérer les questions par ID de quiz
    public List<Question> findByQuizId(Long quizId) {
        return questiondao.findByQuizId(quizId);
    }
}
