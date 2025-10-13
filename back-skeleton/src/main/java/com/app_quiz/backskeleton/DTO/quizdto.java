package com.app_quiz.backskeleton.DTO;

import java.util.List;

public class quizdto {
    private Long id;
    private String title;
    private String subject;
    private List<questiondto> questions;

    public quizdto() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public List<questiondto> getQuestions() { return questions; }
    public void setQuestions(List<questiondto> questions) { this.questions = questions; }
}
