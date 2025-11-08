package com.app_quiz.backskeleton.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private boolean trueFalse;   // true = vrai/faux
    private String correctAnswer;
    private int points;
    private String type;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference // 🔥 empêche la boucle infinie
    private Quiz quiz;

    public Question() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getChoice1() { return choice1; }
    public void setChoice1(String choice1) { this.choice1 = choice1; }

    public String getChoice2() { return choice2; }
    public void setChoice2(String choice2) { this.choice2 = choice2; }

    public String getChoice3() { return choice3; }
    public void setChoice3(String choice3) { this.choice3 = choice3; }

    public String getChoice4() { return choice4; }
    public void setChoice4(String choice4) { this.choice4 = choice4; }

    public boolean isTrueFalse() { return trueFalse; }
    public void setTrueFalse(boolean trueFalse) { this.trueFalse = trueFalse; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
