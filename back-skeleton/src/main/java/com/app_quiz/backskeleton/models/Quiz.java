package com.app_quiz.backskeleton.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String description;
    private String level;      // ✅ niveau : Facile, Moyen, Difficile
    private int players;       // ✅ nombre de joueurs
    private int duration;      // ✅ durée du quiz (secondes)

    // 🔹 Relation avec l’utilisateur (créateur du quiz)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_id")
    @JsonIgnoreProperties({"quizzes", "scores"}) // empêche la boucle JSON infinie
    private User createdBy;

    // 🔹 Relation avec les questions
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions;

    public Quiz() {}

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public int getPlayers() { return players; }
    public void setPlayers(int players) { this.players = players; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}
