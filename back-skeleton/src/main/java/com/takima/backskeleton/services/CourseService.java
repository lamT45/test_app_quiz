package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.CourseDao;
import com.takima.backskeleton.models.users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {
    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<users> findAll() {
        return courseDao.findAll();
    }
}
