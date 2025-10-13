package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.UserDao;
import com.takima.backskeleton.models.users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {
    private final UserDao userDao;

    public CourseService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<users> findAll() {
        return userDao.findAll();
    }
}
