package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score_obtained;
    private int time_taken_seconds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private quiz quiz;

    public score() {}

    // Getters et Setters
    public Long getId() { return id; }
    public int getScore_obtained() { return score_obtained; }
    public void setScore_obtained(int score_obtained) { this.score_obtained = score_obtained; }
    public int getTime_taken_seconds() { return time_taken_seconds; }
    public void setTime_taken_seconds(int time_taken_seconds) { this.time_taken_seconds = time_taken_seconds; }
}
