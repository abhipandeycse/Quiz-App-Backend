package com.question.questionservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.question.questionservice.model.Question;
import com.question.questionservice.model.QuestionWrapper;
import com.question.questionservice.model.Response;
import com.question.questionservice.service.QuestionService;



@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions(); }
		
		
	@GetMapping("category/{category}") 
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("addQuestion") 
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		return questionService.addQuestion(question);

	}
	@PutMapping("updateQuestion") 
	public ResponseEntity<String> updateQuestion(@RequestBody Question question){
		return questionService.updateQuestion(question);
	}
	@DeleteMapping("deleteQuestion/{id}") 
	public ResponseEntity<String> deleteQuestion(@PathVariable int id){
		return questionService.deleteQuestion(id);
	}
	
	@GetMapping("generate") 
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions){
		return questionService.getQuestionsForQuiz(categoryName,numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
		}
	
	
}