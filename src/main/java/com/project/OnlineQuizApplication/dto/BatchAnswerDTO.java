package com.project.OnlineQuizApplication.dto;

import java.util.List;

import com.project.OnlineQuizApplication.model.Answer;

public class BatchAnswerDTO {
    private Long userId;  // New field
    private List<Answer> answers;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
