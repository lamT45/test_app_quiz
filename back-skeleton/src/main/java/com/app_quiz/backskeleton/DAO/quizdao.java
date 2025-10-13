package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface quizdao extends JpaRepository<quiz, Long> {
}
