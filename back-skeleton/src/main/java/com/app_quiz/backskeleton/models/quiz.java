package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subject;
    private int total_score;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private user created_by;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<question> questions;

    public quiz() {}

    // Getters et Setters
    public Long getId() { return id; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public int getTotal_score() { return total_score; }
    public void setTotal_score(int total_score) { this.total_score = total_score; }
    public user getCreated_by() { return created_by; }
    public void setCreated_by(user created_by) { this.created_by = created_by; }
}
