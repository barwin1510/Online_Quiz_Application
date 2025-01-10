package com.project.OnlineQuizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.OnlineQuizApplication.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
