package com.app_quiz.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question_text;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private boolean isTrueFalse;
    private String correct_answer;
    private int points; // 4 points ou 2 points selon type

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private quiz quiz;



    public question() {}

    // Getters et Setters
    public Long getId() { return id; }
    public String getQuestion_text() { return question_text; }
    public void setQuestion_text(String question_text) { this.question_text = question_text; }
    public String getChoice1() { return choice1; }
    public void setChoice1(String choice1) { this.choice1 = choice1; }
    public String getChoice2() { return choice2; }
    public void setChoice2(String choice2) { this.choice2 = choice2; }
    public String getChoice3() { return choice3; }
    public void setChoice3(String choice3) { this.choice3 = choice3; }
    public String getChoice4() { return choice4; }
    public void setChoice4(String choice4) { this.choice4 = choice4; }
    public String getCorrect_answer() { return correct_answer; }
    public void setCorrect_answer(String correct_answer) { this.correct_answer = correct_answer; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
