package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.questiondao;
import com.app_quiz.backskeleton.DAO.quizdao;
import com.app_quiz.backskeleton.DAO.scoredao;
import com.app_quiz.backskeleton.models.question;
import com.app_quiz.backskeleton.models.quiz;
import com.app_quiz.backskeleton.models.score;
import com.app_quiz.backskeleton.models.user;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class scoreservice {

    private final scoredao scoredao;
    private final quizdao quizdao;
    private final questiondao questiondao;

    public scoreservice(scoredao scoredao, quizdao quizdao, questiondao questiondao) {
        this.scoredao = scoredao;
        this.quizdao = quizdao;
        this.questiondao = questiondao;
    }

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

    // 🔹 Méthodes manquantes
    public List<score> findScoresByUserId(Long userId) {
        return scoredao.findByUserId(userId);
    }

    public List<score> findScoresByQuizId(Long quizId) {
        return scoredao.findByQuizId(quizId);
    }

    /**
     * Calcul automatique du score total et du temps total pour un quiz
     */
    public score calculateAndSaveScore(user u, quiz qz, List<String> userAnswers, List<Integer> timesTaken) {
        List<question> questions = qz.getQuestions();

        double totalScore = 0;
        int totalTime = 0;

        for (int i = 0; i < questions.size(); i++) {
            question q = questions.get(i);
            String userAnswer = userAnswers.get(i);
            int time = timesTaken.get(i);

            // Si >30s → 0 points
            if (time > 30) continue;

            // Vrai/Faux = 2 pts, sinon 4 pts
            int maxPoints = q.getType().equalsIgnoreCase("vrai/faux") ? 2 : 4;

            if (q.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {
                totalScore += maxPoints;
            }

            totalTime += time;
        }

        score finalScore = new score();
        finalScore.setUser(u);
        finalScore.setQuiz(qz);
        finalScore.setScore_obtained((int) totalScore);
        finalScore.setTime_taken_seconds(totalTime);

        return scoredao.save(finalScore);
    }
}
