package com.question.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.question.questionservice.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<com.question.questionservice.model.Question, Integer>
{

	List<com.question.questionservice.model.Question> findByCategory(String category);
	
	@Query(value="SELECT q.id FROM Question q Where q.category=:category ORDER BY RAND() LIMIT :numQuestions",nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(String category,int numQuestions);
}
