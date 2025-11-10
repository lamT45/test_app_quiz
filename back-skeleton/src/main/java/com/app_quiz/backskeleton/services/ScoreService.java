package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.QuestionDao;
import com.app_quiz.backskeleton.DAO.QuizDao;
import com.app_quiz.backskeleton.DAO.ScoreDao;
import com.app_quiz.backskeleton.models.Question;
import com.app_quiz.backskeleton.models.Quiz;
import com.app_quiz.backskeleton.models.Score;
import com.app_quiz.backskeleton.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreDao scoredao;
    private final QuizDao quizdao;
    private final QuestionDao questiondao;

    public ScoreService(ScoreDao scoredao, QuizDao quizdao, QuestionDao questiondao) {
        this.scoredao = scoredao;
        this.quizdao = quizdao;
        this.questiondao = questiondao;
    }

    public List<Score> findAllScores() {
        return scoredao.findAll();
    }

    public Optional<Score> findScoreById(Long id) {
        return scoredao.findById(id);
    }

    public Score saveScore(Score s) {
        return scoredao.save(s);
    }

    public void deleteScore(Long id) {
        scoredao.deleteById(id);
    }

    // 🔹 Méthodes manquantes
    public List<Score> findScoresByUserId(Long userId) {
        return scoredao.findByUserId(userId);
    }

    public List<Score> findScoresByQuizId(Long quizId) {
        return scoredao.findByQuizId(quizId);
    }

    /**
     * Calcul automatique du score total et du temps total pour un quiz
     */
    public Score calculateAndSaveScore(User u, Quiz qz, List<String> userAnswers, List<Integer> timesTaken) {
        List<Question> questions = qz.getQuestions();

        double totalScore = 0;
        int totalTime = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
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

        Score finalScore = new Score();
        finalScore.setUser(u);
        finalScore.setQuiz(qz);
        finalScore.setScore_obtained((int) totalScore);
        finalScore.setTime_taken_seconds(totalTime);

        return scoredao.save(finalScore);
    }
    // ==============================
// 🔹 Supprimer tous les scores d’un utilisateur
// ==============================
    public void deleteScoresByUserId(Long userId) {
        scoredao.deleteByUserId(userId);
    }

}