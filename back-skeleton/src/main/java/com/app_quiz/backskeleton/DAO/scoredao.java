package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface scoredao extends JpaRepository<score, Long> {
    List<score> findByUserId(Long userId);
    List<score> findByQuizId(Long quizId);
}
