package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<users, Long> {
}
