package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Long> {
    @Query("SELECT DISTINCT q.category FROM Quiz q")
    List<String> findDistinctCategories();

}