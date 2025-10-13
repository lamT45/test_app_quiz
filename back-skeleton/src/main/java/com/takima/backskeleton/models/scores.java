package com.quizapp.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float score;
    private Integer timeTaken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Score() {}

    public Long getId() { return id; }
    public Float getScore() { return score; }
    public Integer getTimeTaken() { return timeTaken; }
    public User getUser() { return user; }
    public Quiz getQuiz() { return quiz; }
}