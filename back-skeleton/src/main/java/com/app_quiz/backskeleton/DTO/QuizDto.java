package com.app_quiz.backskeleton.DTO;

import java.util.List;

public class QuizDto {

    private Long id;
    private String title;
    private String category;
    private String description;
    private String level;
    private int players;
    private int duration;
    private Long createdById;
    private String createdByName;
    private String createdByEmail;
    private List<Long> questionIds;

    public QuizDto() {}

    // ✅ Getters et Setters
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

    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }

    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }

    public String getCreatedByEmail() { return createdByEmail; }
    public void setCreatedByEmail(String createdByEmail) { this.createdByEmail = createdByEmail; }

    public List<Long> getQuestionIds() { return questionIds; }
    public void setQuestionIds(List<Long> questionIds) { this.questionIds = questionIds; }
}
