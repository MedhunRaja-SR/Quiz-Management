package com.mfpe.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion {
	
	private int questionId;
	private String question;
	private String courseType;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String response;
	
}
