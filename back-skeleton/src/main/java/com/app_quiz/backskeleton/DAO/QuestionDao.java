package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {
    List<Question> findByQuizId(Long quizId);
}
