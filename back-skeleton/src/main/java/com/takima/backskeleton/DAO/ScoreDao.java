package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.questions;
import com.takima.backskeleton.models.quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDao extends JpaRepository<questions, Long> {
    @Query("SELECT m.students FROM questions m WHERE m.id= :majorId")
    List<quizzes> getAllStudentsFromMajor(Long majorId);
}
