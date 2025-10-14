package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface questiondao extends JpaRepository<question, Long> {
    List<question> findByQuizId(Long quizId);
}
