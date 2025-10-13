package com.takima.backskeleton.DTO;

import com.takima.backskeleton.models.questions;
import com.takima.backskeleton.models.users;

import java.time.Instant;
import java.util.List;
public class UserDto {
    private String firstName;
    private String lastName;
    private Instant birthdate;
    private List<users> courses;
    private questions questions;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public List<users> getCourses() {
        return courses;
    }

    public questions getMajor() {
        return questions;
    }

    public static final class StudentDtoBuilder {
        private String firstName;
        private String lastName;
        private Instant birthdate;
        private List<users> courses;
        private questions questions;

        public StudentDtoBuilder() {
        }

        public static StudentDtoBuilder aStudentDto() {
            return new StudentDtoBuilder();
        }

        public StudentDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentDtoBuilder birthdate(Instant birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public StudentDtoBuilder courses(List<users> courses) {
            this.courses = courses;
            return this;
        }

        public StudentDtoBuilder major(questions questions) {
            this.questions = questions;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.lastName = this.lastName;
            userDto.questions = this.questions;
            userDto.firstName = this.firstName;
            userDto.birthdate = this.birthdate;
            userDto.courses = this.courses;
            return userDto;
        }
    }
}
