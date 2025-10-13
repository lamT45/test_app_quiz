package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.ScoreDao;
import com.takima.backskeleton.models.questions;
import com.takima.backskeleton.models.quizzes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MajorService {
    private final ScoreDao scoreDao;

    public MajorService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public List<questions> findAll() {
        Iterable<questions> it = scoreDao.findAll();
        List <questions> questions = new ArrayList<>();
        it.forEach(questions::add);
        return questions;
    }

    public List<quizzes> getStudentsOfMajor(Long id) {
        return scoreDao.getAllStudentsFromMajor(id);
    }
}
