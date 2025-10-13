package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.QuestionDao;
import com.takima.backskeleton.DTO.UserDto;
import com.takima.backskeleton.DTO.UserMapper;
import com.takima.backskeleton.models.quizzes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final QuestionDao questionDao;

    public StudentService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<quizzes> findAll() {
        Iterable<quizzes> it = questionDao.findAll();
        List <quizzes> users = new ArrayList<>();
        it.forEach(users::add);
        return users ;
    }

    public quizzes getById(Long id) {
        return questionDao.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        questionDao.deleteById(id);
    }

    @Transactional
    public void addStudent(UserDto userDto) {
        quizzes quizzes;
        try {
            quizzes = UserMapper.fromDto(userDto, null);
        } catch (IOException e) {
            throw new RuntimeException("Error with quizzes image", e);
        }

        questionDao.save(quizzes);
    }

    @Transactional
    public void updateStudent(UserDto userDto, Long id) {
        questionDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("quizzes doesn't exist"));
        quizzes quizzes;
        try {
            quizzes = UserMapper.fromDto(userDto, id);
        } catch (IOException e) {
            throw new RuntimeException("Error with quizzes image", e);
        }
        questionDao.save(quizzes);
    }

    public List<quizzes> searchByMajorAndCourse(int majorId, int courseId) {
        return questionDao.findByMajorIdAndCourseId(majorId, courseId);
    }
}
