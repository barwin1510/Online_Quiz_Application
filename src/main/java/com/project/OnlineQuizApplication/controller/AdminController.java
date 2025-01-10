package com.project.OnlineQuizApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.OnlineQuizApplication.model.Quiz;
import com.project.OnlineQuizApplication.model.Question;
import com.project.OnlineQuizApplication.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private QuizService quizService;

    // Create a quiz
    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    // Get all quizzes
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // Add question to a quiz
    @PostMapping("/quizzes/{quizId}/questions")
    public Question addQuestionToQuiz(@PathVariable Long quizId, @RequestBody Question question) {
        return quizService.addQuestionToQuiz(quizId, question);
    }

    // Get all questions for a specific quiz
    @GetMapping("/quizzes/{quizId}/questions")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return quizService.getQuestionsByQuiz(quizId);
    }
}
