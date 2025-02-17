package com.quiz.quizservice.model;

import java.util.List;
import org.springframework.stereotype.Component;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Component
@Table(name="quiz")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ElementCollection 
	private List<Integer> questionIds;
	public Quiz() {
		super();
	}
	
	public Quiz(int id, String title, List<Integer> questionIds) {
		super();
		this.id = id;
		this.title = title;
		this.questionIds = questionIds;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Integer> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", questionIds=" + questionIds + "]";
	}

	
	
}
