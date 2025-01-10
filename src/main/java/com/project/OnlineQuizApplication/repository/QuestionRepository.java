package com.project.OnlineQuizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.OnlineQuizApplication.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
