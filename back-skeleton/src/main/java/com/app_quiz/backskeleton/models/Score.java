package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score_obtained;
    private int time_taken_seconds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Score() {}

    //  Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { // <-- c’est ça qu’il manquait
        this.id = id;
    }

    public int getScore_obtained() {
        return score_obtained;
    }

    public void setScore_obtained(int score_obtained) {
        this.score_obtained = score_obtained;
    }

    public int getTime_taken_seconds() {
        return time_taken_seconds;
    }

    public void setTime_taken_seconds(int time_taken_seconds) {
        this.time_taken_seconds = time_taken_seconds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}