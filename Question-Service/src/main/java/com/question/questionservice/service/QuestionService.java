package com.question.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.question.questionservice.model.Question;
import com.question.questionservice.model.QuestionWrapper;
import com.question.questionservice.model.Response;
import com.question.questionservice.repository.QuestionRepo;


@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepo questionRepo;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST );
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
		return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
	}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); 
		}

	public ResponseEntity<String> addQuestion(Question question) {
		questionRepo.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<String> updateQuestion(Question question) {
		questionRepo.save(question);
		return new ResponseEntity<>("updated",HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> deleteQuestion(int id) {
		questionRepo.deleteById(id);
		return new ResponseEntity<>("deleted",HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {
		List<Integer> questions = questionRepo.findRandomQuestionsByCategory(categoryName, numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions =new ArrayList<>();
		for (Integer id : questionIds ) {
			questions.add(questionRepo.findById(id).get());
		}
		for (Question question : questions ) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestion_title(question.getQuestion_title());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right = 0;
		for (Response response :responses) {
			Question question = questionRepo.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRight_answer()))
			right++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	} 

}
