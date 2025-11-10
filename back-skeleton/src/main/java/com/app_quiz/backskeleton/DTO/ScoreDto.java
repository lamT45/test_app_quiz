package com.app_quiz.backskeleton.DTO;

import java.util.List;

public class ScoreDto {
    private Long userId;
    private Long quizId;
    private int timeTakenSeconds;
    private List<String> answers;

    public ScoreDto() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public int getTimeTakenSeconds() { return timeTakenSeconds; }
    public void setTimeTakenSeconds(int timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }

    public List<String> getAnswers() { return answers; }
    public void setAnswers(List<String> answers) { this.answers = answers; }

    @Override
    public String toString() {
        return "ScoreDto{" +
                "userId=" + userId +
                ", quizId=" + quizId +
                ", timeTakenSeconds=" + timeTakenSeconds +
                ", answers=" + answers +
                '}';
    }
}
