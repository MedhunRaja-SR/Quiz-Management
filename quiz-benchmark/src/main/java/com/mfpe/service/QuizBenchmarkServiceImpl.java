package com.mfpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.model.QuizBenchmark;
import com.mfpe.repository.QuizBenchmarkRepo;


@Service
public class QuizBenchmarkServiceImpl implements QuizBenchmarkService {
	
	@Autowired
	QuizBenchmarkRepo quizBenchmarkRepo;

	public List<QuizBenchmark> getQuizBenchmarkList() {
		return quizBenchmarkRepo.findAll();
	}
	
}
