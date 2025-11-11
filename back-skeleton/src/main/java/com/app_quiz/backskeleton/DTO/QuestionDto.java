package com.app_quiz.backskeleton.DTO;

public class QuestionDto {
    private Long id;
    private String questionText;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private boolean trueFalse;
    private String correctAnswer;
    private int points;
    private Long quizId;

    public QuestionDto() {}

    public QuestionDto(Long id, String questionText, String choice1, String choice2, String choice3, String choice4,
                       boolean trueFalse, String correctAnswer, int points, Long quizId) {
        this.id = id;
        this.questionText = questionText;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.trueFalse = trueFalse;
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.quizId = quizId;
    }

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

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }
}
