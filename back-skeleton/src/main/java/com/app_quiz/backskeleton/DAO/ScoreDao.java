package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreDao extends JpaRepository<Score, Long> {
    List<Score> findByUserId(Long userId);
    List<Score> findByQuizId(Long quizId);

    // Ajoute cette ligne si elle n’existe pas 👇
    void deleteByUserId(Long userId);
}
