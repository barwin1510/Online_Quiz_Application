package com.project.OnlineQuizApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.OnlineQuizApplication.dto.BatchAnswerDTO;
import com.project.OnlineQuizApplication.dto.QuestionDTO;
import com.project.OnlineQuizApplication.dto.QuizDTO;
import com.project.OnlineQuizApplication.model.Answer;
import com.project.OnlineQuizApplication.model.Question;
import com.project.OnlineQuizApplication.model.Quiz;
import com.project.OnlineQuizApplication.model.UserScore;
import com.project.OnlineQuizApplication.repository.UserScoreRepository;
import com.project.OnlineQuizApplication.service.QuizService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuizService quizService;
    
    @Autowired
    private UserScoreRepository userScoreRepository;

    // Endpoint to fetch all questions for the user
    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = quizService.getAllQuestions();
        return questions.stream()
                       .map(q -> new QuestionDTO(q.getId(), q.getQuestion(), q.getChoice1(), q.getChoice2(), q.getChoice3(), q.getChoice4()))
                       .collect(Collectors.toList());
    }

    @PostMapping("/submit-answers")
    public Map<String, Object> submitAnswers(@RequestBody BatchAnswerDTO batchAnswerDTO) {
        List<Answer> answers = batchAnswerDTO.getAnswers();
        Long userId = batchAnswerDTO.getUserId();
        Map<Long, String> resultMap = new HashMap<>();
        
        int correctAnswers = 0;
        for (Answer answer : answers) {
            boolean isCorrect = quizService.checkAnswer(answer.getQuestionId(), answer.getSelectedChoice());
            resultMap.put(answer.getQuestionId(), isCorrect ? "Correct" : "Wrong");
            if (isCorrect) {
                correctAnswers++;
            }
        }
        
        // Save the score for the user
        UserScore userScore = new UserScore();
        userScore.setUserId(userId);
        userScore.setScore(correctAnswers);
        userScoreRepository.save(userScore);
        
        // Prepare the response
        int totalQuestions = answers.size();
        Map<String, Object> response = new HashMap<>();
        response.put("results", resultMap);
        response.put("score", correctAnswers + "/" + totalQuestions);
        
        return response;
    }

 // Get all questions for a specific quiz
    @GetMapping("/quizzes/{quizId}/questions")
    public List<QuestionDTO> getQuestionsForQuiz(@PathVariable Long quizId) {
        List<Question> questions = quizService.getQuestionsByQuiz(quizId);
        return questions.stream()
                .map(q -> new QuestionDTO(q.getId(), q.getQuestion(), q.getChoice1(), q.getChoice2(), q.getChoice3(), q.getChoice4()))
                .collect(Collectors.toList());
    }

    // Endpoint to get the user's score
    @GetMapping("/score/{userId}")
    public int getUserScore(@PathVariable Long userId) {
        UserScore userScore = userScoreRepository.findByUserId(userId);
        return userScore != null ? userScore.getScore() : 0;
    }
    
 // Endpoint to fetch all quizzes for the user
    @GetMapping("/quizzes")
    public List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes(); // Assuming you have a method in QuizService to get quizzes
        return quizzes.stream()
                      .map(q -> new QuizDTO(q.getId(), q.getName())) // Map Quiz to QuizDTO
                      .collect(Collectors.toList());
    }
}
