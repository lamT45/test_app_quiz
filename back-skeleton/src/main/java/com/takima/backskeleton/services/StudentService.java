package com.takima.backskeleton.services;

import com.takima.backskeleton.DAO.StudentDao;
import com.takima.backskeleton.DTO.StudentDto;
import com.takima.backskeleton.DTO.StudentMapper;
import com.takima.backskeleton.models.quizzes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<quizzes> findAll() {
        Iterable<quizzes> it = studentDao.findAll();
        List <quizzes> users = new ArrayList<>();
        it.forEach(users::add);
        return users ;
    }

    public quizzes getById(Long id) {
        return studentDao.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        studentDao.deleteById(id);
    }

    @Transactional
    public void addStudent(StudentDto studentDto) {
        quizzes quizzes;
        try {
            quizzes = StudentMapper.fromDto(studentDto, null);
        } catch (IOException e) {
            throw new RuntimeException("Error with quizzes image", e);
        }

        studentDao.save(quizzes);
    }

    @Transactional
    public void updateStudent(StudentDto studentDto, Long id) {
        studentDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("quizzes doesn't exist"));
        quizzes quizzes;
        try {
            quizzes = StudentMapper.fromDto(studentDto, id);
        } catch (IOException e) {
            throw new RuntimeException("Error with quizzes image", e);
        }
        studentDao.save(quizzes);
    }

    public List<quizzes> searchByMajorAndCourse(int majorId, int courseId) {
        return studentDao.findByMajorIdAndCourseId(majorId, courseId);
    }
}
