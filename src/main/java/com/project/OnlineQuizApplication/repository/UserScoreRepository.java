package com.project.OnlineQuizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.OnlineQuizApplication.model.UserScore;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    UserScore findByUserId(Long userId);
}
