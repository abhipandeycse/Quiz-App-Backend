package com.quiz.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizservice.feign.QuizFeign;
import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.Quiz;
import com.quiz.quizservice.model.Response;
import com.quiz.quizservice.repository.QuizRepo;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuizFeign quizInterface;
	
	/*@Autowired
	private QuestionRepo questionRepo;*/

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questionsIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionsIds);
		quizRepo.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
	  Quiz quiz = quizRepo.findById(id).get();
	  List<Integer> questionsIds= quiz.getQuestionIds();
	  ResponseEntity<List<QuestionWrapper>> questionsForUser=quizInterface.getQuestionsFromId(questionsIds);
	  return questionsForUser;
	  }	

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		 ResponseEntity<Integer> score= quizInterface.getScore(responses);	
		return score;

	}
	
}
