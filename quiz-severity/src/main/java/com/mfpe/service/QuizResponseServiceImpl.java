package com.mfpe.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.model.QuizBenchmark;
import com.mfpe.model.QuizQuestion;
import com.mfpe.model.QuizRequest;
import com.mfpe.model.QuizResponse;
import com.mfpe.repository.QuizResponseRepo;

@Service
public class QuizResponseServiceImpl implements QuizResponseService {

	@Autowired
	private QuizResponseRepo quizResponseRepo;

	Logger logger = LoggerFactory.getLogger("Severity-Response-Calculation");

	// This method is to check the audit responses with the audit type
	public QuizResponse createQuizResponse(List<QuizQuestion> questions, List<String> ownAnswer) {

		int score = 0;
		String courseType = questions.get(0).getCourseType();
		QuizResponse quizResponse = new QuizResponse();
		ArrayList<String> answer = new ArrayList<String>();
		// storing the responses in ArrayList
		for (QuizQuestion q : questions) {
			answer.add(q.getResponse());
		}

		logger.info(String.format("Audit-type : %s", courseType));

		/*
		 * Here we check actual condition of the severity microservice Determines the
		 * project execution status and the remediation duration detail
		 */
	
		// returns the common elements in both list
		answer.retainAll(ownAnswer);
		score = answer.size();
		
		// Assigning Score and CourseType...
		quizResponse.setScore(score);
		quizResponse.setCourseType(courseType);

		// calling the result method.
		quizResponse = getResult(score, quizResponse);

		return quizResponse;
	}

	public QuizResponse getResult(int score, QuizResponse quizResponse) {

		if (score <= 1) {
			quizResponse.setStatus("Poor");
			quizResponse.setSuggestion("Need To Work Hard");
		}

		else if (score < 4 && score > 1) {
			quizResponse.setStatus("Better");
			quizResponse.setSuggestion("Need Some Concentration");
		}

		else if (score == 4) {
			quizResponse.setStatus("Good");
			quizResponse.setSuggestion("small improvement needed");
		}

		else {
			quizResponse.setStatus("Excellent");
			quizResponse.setSuggestion("Keep Going");
		}
		return quizResponse;

	}

	@Override
	public QuizResponse getQuizResponse(List<QuizBenchmark> benchmarkList, String courseType,
			List<QuizQuestion> questionResponses) {
		// TODO Auto-generated method stub
		QuizResponse quizResponse = null;
		List<String> answerC = new ArrayList<String>(
				Arrays.asList("LowerCase", "External", "true", "No Limits", "Dennis Ritchie"));
		List<String> answerJava = new ArrayList<String>(
				Arrays.asList(".class", "JDK", "Object class", "JAVA_HOME", "James Gosling"));
		List<String> answerPython = new ArrayList<String>(Arrays.asList("4", "All", "7", "No", "Guido van Rossum"));
		List<String> answerSql = new ArrayList<String>(
				Arrays.asList("DEFAULT", "TRUNCATE", "Binary Large Objects", "4", "Candidate Key"));
		List<String> answerJs = new ArrayList<String>(Arrays.asList("Both", "Undefined", "Object", "All", "Number"));
		
			if (courseType.equals("C")) {
				quizResponse = createQuizResponse(questionResponses, answerC);

			} else if (courseType.equalsIgnoreCase("Java")) {
				quizResponse = createQuizResponse(questionResponses, answerJava);
				
			} else if (courseType.equalsIgnoreCase("Python")) {
				quizResponse = createQuizResponse(questionResponses, answerPython);
			
			} else if (courseType.equalsIgnoreCase("SQL")) {
				quizResponse = createQuizResponse(questionResponses, answerSql);
		
			} else {
				quizResponse = createQuizResponse(questionResponses, answerJs);
		
			}

		// saving to the repo
		// auditResponseRepo.save(auditResponse);
		return quizResponse;
	}

	@Override
	public QuizResponse saveQuizResponse(QuizResponse quizResponse, QuizRequest quizRequest) {
		// TODO Auto-generated method stub
		// setting project name and manager name
		quizResponse.setUserName(quizRequest.getUserName());
		quizResponse.setCreationDateTime(new Date());
		logger.info("Saving Audit-Response in DB :: " + quizRequest);
		return quizResponseRepo.save(quizResponse);
	}

	@Override
	public List<QuizResponse> getDetails() {
		return quizResponseRepo.findAll();
	}
}
