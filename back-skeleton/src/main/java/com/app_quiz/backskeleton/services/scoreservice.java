package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.models.score;
import com.app_quiz.backskeleton.models.quiz;
import com.app_quiz.backskeleton.models.question;
import com.app_quiz.backskeleton.DAO.scoredao;
import com.app_quiz.backskeleton.DAO.quizdao;
import com.app_quiz.backskeleton.DAO.questiondao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class scoreservice {

    @Autowired
    private scoredao scoredao;

    @Autowired
    private quizdao quizdao;

    @Autowired
    private questiondao questiondao;

    public List<score> findAllScores() {
        return scoredao.findAll();
    }

    public Optional<score> findScoreById(Long id) {
        return scoredao.findById(id);
    }

    public score saveScore(score s) {
        return scoredao.save(s);
    }

    public void deleteScore(Long id) {
        scoredao.deleteById(id);
    }

    /**
     * Calcul automatique du score total et du temps total pour un quiz
     */
    public score calculateAndSaveScore(Long userId, Long quizId, List<String> userAnswers, List<Integer> timesTaken) {
        Optional<quiz> quizOpt = quizdao.findById(quizId);
        if (quizOpt.isEmpty()) {
            throw new RuntimeException("Quiz non trouvé avec l'id : " + quizId);
        }

        quiz qz = quizOpt.get();
        List<question> questions = qz.getQuestions();

        double totalScore = 0;
        int totalTime = 0;

        for (int i = 0; i < questions.size(); i++) {
            question q = questions.get(i);
            String userAnswer = userAnswers.get(i);
            int time = timesTaken.get(i);

            // Si l'utilisateur a dépassé 30 secondes, on met 0 points
            if (time > 30) continue;

            // Vrai/Faux = 2 points, sinon 4 points
            int maxPoints = q.getType().equalsIgnoreCase("vrai/faux") ? 2 : 4;

            // Si bonne réponse
            if (q.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
                totalScore += maxPoints;
            }

            totalTime += time;
        }

        score finalScore = new score();
        finalScore.setUserId(userId);
        finalScore.setQuizId(quizId);
        finalScore.setScore(totalScore);
        finalScore.setTimeTaken(totalTime);

        return scoredao.save(finalScore);
    }
}
