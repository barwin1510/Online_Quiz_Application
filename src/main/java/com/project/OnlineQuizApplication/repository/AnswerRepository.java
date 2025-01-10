package com.project.OnlineQuizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.OnlineQuizApplication.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
