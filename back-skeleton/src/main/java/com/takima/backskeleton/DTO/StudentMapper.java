package com.takima.backskeleton.DTO;

import com.takima.backskeleton.models.quizzes;

import java.io.IOException;

public class StudentMapper {
    public static quizzes fromDto(StudentDto dto, Long id) throws IOException {
        return new quizzes.Builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthdate(dto.getBirthdate())
                .courses(dto.getCourses())
                .major(dto.getMajor())
                .build();
    }

    public static StudentDto toDto (quizzes quizzes){
        return new StudentDto.StudentDtoBuilder()
                .firstName(quizzes.getFirstName())
                .lastName(quizzes.getLastName())
                .birthdate(quizzes.getBirthdate())
                .courses(quizzes.getCourses())
                .major(quizzes.getMajor())
                .build();
    }
}
