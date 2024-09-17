package com.quiz.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizservice.model.Quiz;


@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
