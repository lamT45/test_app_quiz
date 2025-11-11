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

    //  CRUD basique
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

    public List<Score> findScoresByUserId(Long userId) {
        return scoredao.findByUserId(userId);
    }

    public List<Score> findScoresByQuizId(Long quizId) {
        return scoredao.findByQuizId(quizId);
    }

    // =============================================================
    //  Calcul automatique du score en fonction des réponses + temps
    // =============================================================
    public Score calculateAndSaveScore(User user, Quiz quiz, List<String> userAnswers, int totalTimeSeconds) {
        List<Question> questions = quiz.getQuestions();

        int totalScore = 0;
        int totalPointsPossible = 0;

        //  Parcours des questions pour comparer les réponses
        for (int i = 0; i < questions.size() && i < userAnswers.size(); i++) {
            Question q = questions.get(i);
            String givenAnswer = userAnswers.get(i);

            totalPointsPossible += q.getPoints();

            if (givenAnswer != null &&
                    givenAnswer.trim().equalsIgnoreCase(q.getCorrectAnswer().trim())) {
                totalScore += q.getPoints();
            }
        }

        //  Ajustement selon le temps total (bonus/malus)
        // Exemple : plus tu es rapide, plus ton score final est boosté
        int quizDuration = quiz.getDuration(); // en secondes depuis la table quizzes
        double timeRatio = (double) totalTimeSeconds / (quizDuration * questions.size());

        // Limite pour éviter un score négatif ou trop élevé
        double efficiency = Math.max(0.5, Math.min(1.5, 1.2 - timeRatio));
        int finalScore = (int) Math.round(totalScore * efficiency);

        // Création et sauvegarde du score
        Score finalScoreObj = new Score();
        finalScoreObj.setUser(user);
        finalScoreObj.setQuiz(quiz);
        finalScoreObj.setScore_obtained(finalScore);
        finalScoreObj.setTime_taken_seconds(totalTimeSeconds);

        return scoredao.save(finalScoreObj);
    }
    // ==============================
// 🔹 Supprimer tous les scores d’un utilisateur
// ==============================
    public void deleteScoresByUserId(Long userId) {
        scoredao.deleteByUserId(userId);
    }
}
