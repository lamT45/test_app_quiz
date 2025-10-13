package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String role; // "ADMIN" ou "USER"
    private LocalDateTime created_at = LocalDateTime.now();

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    private List<quiz> quizzes;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<score> scores;

    public user() {}

    // Getters et Setters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDateTime getCreated_at() { return created_at; }
}
