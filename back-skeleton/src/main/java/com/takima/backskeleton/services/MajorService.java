package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.MajorDao;
import com.takima.backskeleton.models.questions;
import com.takima.backskeleton.models.quizzes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MajorService {
    private final MajorDao majorDao;

    public MajorService(MajorDao majorDao) {
        this.majorDao = majorDao;
    }

    public List<questions> findAll() {
        Iterable<questions> it = majorDao.findAll();
        List <questions> questions = new ArrayList<>();
        it.forEach(questions::add);
        return questions;
    }

    public List<quizzes> getStudentsOfMajor(Long id) {
        return majorDao.getAllStudentsFromMajor(id);
    }
}
