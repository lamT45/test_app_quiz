package com.app_quiz.backskeleton.DTO;

public class questiondto {
    private Long id;
    private String text;
    private String choices; // JSON ou texte contenant les 4 choix
    private String correctAnswer;
    private int points;
    private int timeLimitSeconds;

    public questiondto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getChoices() { return choices; }
    public void setChoices(String choices) { this.choices = choices; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public int getTimeLimitSeconds() { return timeLimitSeconds; }
    public void setTimeLimitSeconds(int timeLimitSeconds) { this.timeLimitSeconds = timeLimitSeconds; }
}
