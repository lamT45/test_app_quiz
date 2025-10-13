package com.takima.backskeleton.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScore;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private user user;

    @ManyToOne
    @JoinColumn(name = "id_quiz", nullable = false)
    private Quiz quiz;

    private int scoreValue;
    private int maxScore;
    private int correctCount;
    private Integer durationSeconds;
    private LocalDateTime playedAt = LocalDateTime.now();

    // Getters / Setters
    public Long getIdScore() { return idScore; }
    public void setIdScore(Long idScore) { this.idScore = idScore; }
    public user getUser() { return user; }
    public void setUser(user user) { this.user = user; }
    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }
    public int getScoreValue() { return scoreValue; }
    public void setScoreValue(int scoreValue) { this.scoreValue = scoreValue; }
    public int getMaxScore() { return maxScore; }
    public void setMaxScore(int maxScore) { this.maxScore = maxScore; }
    public int getCorrectCount() { return correctCount; }
    public void setCorrectCount(int correctCount) { this.correctCount = correctCount; }
}
