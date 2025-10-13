package com.quizapp.backskeleton.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer timeLimitPerQuestionSeconds;
    private LocalDateTime createdAt;

    @Lob
    private String leaderboard; // JSON string

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions;

    @OneToMany(mappedBy = "quiz")
    @JsonIgnore
    private List<Score> scores;

    public Quiz() {}

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getTimeLimitPerQuestionSeconds() { return timeLimitPerQuestionSeconds; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getLeaderboard() { return leaderboard; }
    public User getCreatedBy() { return createdBy; }
    public List<Question> getQuestions() { return questions; }
    public List<Score> getScores() { return scores; }
}