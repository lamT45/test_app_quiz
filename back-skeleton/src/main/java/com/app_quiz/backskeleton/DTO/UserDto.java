package com.app_quiz.backskeleton.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String role; // "ADMIN" ou "USER"
    private LocalDateTime createdAt;
    private List<Long> quizIds; // Les quizzes créés par l'utilisateur
    private List<Long> scoreIds; // Les scores obtenus

    public UserDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Long> getQuizIds() { return quizIds; }
    public void setQuizIds(List<Long> quizIds) { this.quizIds = quizIds; }

    public List<Long> getScoreIds() { return scoreIds; }
    public void setScoreIds(List<Long> scoreIds) { this.scoreIds = scoreIds; }
}
