package com.mfpe.model;

import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class QuizDetail {
	
	private String courseType;
	private Date quizDate;
	private List<QuizQuestion> quizQuestions;
}
