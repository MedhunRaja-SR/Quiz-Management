package com.mfpe.service;

import java.util.List;

import com.mfpe.model.QuizBenchmark;
import com.mfpe.model.QuizResponse;
import com.mfpe.model.QuizQuestion;
import com.mfpe.model.QuizRequest;

public interface QuizResponseService {
	
	public QuizResponse getQuizResponse(List<QuizBenchmark> benchmarkList, String courseType,List<QuizQuestion> questionResponses);

	public QuizResponse saveQuizResponse(QuizResponse quizResponse, QuizRequest quizRequest); 
	
	public List<QuizResponse> getDetails();
		
}
