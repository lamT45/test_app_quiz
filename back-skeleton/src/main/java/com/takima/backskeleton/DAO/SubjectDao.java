package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao extends JpaRepository<quizzes, Long> {
    @Query("SELECT s FROM quizzes s JOIN s.courses c WHERE c.id= :courseId AND s.major.id = :majorId ")
    List<quizzes> findByMajorIdAndCourseId(int majorId, int courseId);
}
