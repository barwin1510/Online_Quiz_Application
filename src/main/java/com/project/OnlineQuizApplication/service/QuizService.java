package com.project.OnlineQuizApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OnlineQuizApplication.model.Question;
import com.project.OnlineQuizApplication.model.Quiz;
import com.project.OnlineQuizApplication.repository.QuestionRepository;
import com.project.OnlineQuizApplication.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public boolean checkAnswer(Long questionId, String selectedChoice) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.map(q -> q.getCorrectAnswer().equalsIgnoreCase(selectedChoice)).orElse(false);
    }
    
 // Add a new quiz
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // Get all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Add question to a quiz
    public Question addQuestionToQuiz(Long quizId, Question question) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            question.setQuiz(quiz.get());
            return questionRepository.save(question);
        }
        return null;
    }

    // Get all questions of a quiz
    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findAll().stream()
                .filter(q -> q.getQuiz().getId().equals(quizId))
                .collect(Collectors.toList());
    }
}
