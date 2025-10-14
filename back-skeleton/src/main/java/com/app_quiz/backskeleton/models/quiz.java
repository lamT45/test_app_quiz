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
    private String category;
    private String description;
    //private int totalScore;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private user createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<question> questions;

    public quiz() {}

    // ✅ Getters et Setters
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    //public int getTotalScore() { return totalScore; }
    //public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

    public user getCreatedBy() { return createdBy; }
    public void setCreatedBy(user createdBy) { this.createdBy = createdBy; }

    public List<question> getQuestions() { return questions; }
    public void setQuestions(List<question> questions) { this.questions = questions; }
}
