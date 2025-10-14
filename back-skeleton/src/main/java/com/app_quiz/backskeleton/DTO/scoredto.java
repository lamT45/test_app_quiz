package com.app_quiz.backskeleton.DTO;

public class scoredto {
    private Long id;
    private Long userId;
    private Long quizId;
    private int scoreObtained;
    private int timeTakenSeconds;

    public scoredto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public int getScoreObtained() { return scoreObtained; }
    public void setScoreObtained(int scoreObtained) { this.scoreObtained = scoreObtained; }

    public int getTimeTakenSeconds() { return timeTakenSeconds; }
    public void setTimeTakenSeconds(int timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }
}
