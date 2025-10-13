package com.app_quiz.backskeleton.DAO;

import com.app_quiz.backskeleton.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userdao extends JpaRepository<user, Long> {
    user findByUsername(String username);
}
