package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Long> {
}
