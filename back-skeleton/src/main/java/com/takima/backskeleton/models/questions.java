package com.quizapp.backskeleton.models;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Lob
    private String text;

    private String type;
    private Integer points;

    @Lob
    private String choices; // JSON avec les options et la bonne réponse

    private String imageUrl;

    public Question() {}

    public Long getId() { return id; }
    public Quiz getQuiz() { return quiz; }
    public String getText() { return text; }
    public String getType() { return type; }
    public Integer getPoints() { return points; }
    public String getChoices() { return choices; }
    public String getImageUrl() { return imageUrl; }
}