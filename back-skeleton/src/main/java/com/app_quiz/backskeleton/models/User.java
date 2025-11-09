package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    @Column(nullable = false)
    private String role = "PLAYER"; //  Valeur par défaut automatique
    private LocalDateTime createdAt = LocalDateTime.now();


    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Score> scores;

    public User() {}

    public void setId(Long id) {
        this.id = id;
    }


    // ✅ Getters et Setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }

    public List<Score> getScores() { return scores; }
    public void setScores(List<Score> scores) { this.scores = scores; }
}
