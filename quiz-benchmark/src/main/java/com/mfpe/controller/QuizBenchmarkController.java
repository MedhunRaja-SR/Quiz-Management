package com.mfpe.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.model.QuizBenchmark;
import com.mfpe.service.QuizBenchmarkService;
import com.mfpe.service.AuthorizationService;

@RestController
@RequestMapping("/benchmark")
@CrossOrigin(origins = "*") // localhost:4200
public class QuizBenchmarkController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private QuizBenchmarkService quizBenchmarkService;

	Logger logger = LoggerFactory.getLogger("Benchmark-Controller-Logger");

	// Endpoint to retrieve the Audit Benchmark details
	@GetMapping("/QuizBenchmark")
	public List<QuizBenchmark> getQuizBenchmark(@RequestHeader("Authorization") String jwt) {
		List<QuizBenchmark> quizBenchmarks = new ArrayList<>();

		// checking if the jwt is valid or not
		logger.info("from header JWT :: " + jwt);
		if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
			quizBenchmarks = quizBenchmarkService.getQuizBenchmarkList();
		} else {
			logger.error("Failed to validate the JWT :: " + jwt);
		}
		return quizBenchmarks;
	}

	// Endpoint to check if the microservice is live
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Quiz Benchmark Microservice is Active";
	}

}
