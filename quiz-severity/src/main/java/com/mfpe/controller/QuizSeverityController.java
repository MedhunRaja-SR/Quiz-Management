package com.mfpe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.feign.QuizBenchmarkFeign;
import com.mfpe.model.CourseType;
import com.mfpe.model.QuizBenchmark;
import com.mfpe.model.QuizQuestion;
import com.mfpe.model.QuizRequest;
import com.mfpe.model.QuizResponse;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuizResponseService;

@RestController
@RequestMapping("/severity")
@CrossOrigin(origins = "*")
public class QuizSeverityController {
	
	@Autowired
	private QuizBenchmarkFeign quizBenchmarkFeign;
	
	@Autowired
	private QuizResponseService quizResponseService;
	
	@Autowired
	private AuthorizationService authorizationService;
	

	Logger logger = LoggerFactory.getLogger("Severity-Controller-Logger");

	//This is to check the microservice is working or not 
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Quiz Severity Microservice is Active";
	}
	
	//This is to check the severity of the audit and it returns the execution status of the project
	@PostMapping("/QuizExecutionStatus")
	public ResponseEntity<?> quizSeverity(@RequestHeader("Authorization") String jwt,
				@RequestBody QuizRequest quizRequest){

		QuizResponse quizResponse = new QuizResponse();
		
		ResponseEntity<QuizResponse> response= null;
		
		// checking if the jwt is valid or not
		// creating auditResponse according to auditRequest
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {				
			logger.info("Successfully validated the JWT :: " + jwt);
			
			// getting benchmark list from Benchmark-MS
			List<QuizBenchmark> benchmarkList = quizBenchmarkFeign.getQuizBenchmark(jwt);
			
			CourseType courseType = new CourseType();
			
			courseType.setCourseType(quizRequest.getQuizDetail().getCourseType());	// setting auditType
			
			// getting responses back from RequestBody
			List<QuizQuestion> questionResponses = quizRequest.getQuizDetail().getQuizQuestions();
			
			// create Audit-response
			quizResponse = quizResponseService.getQuizResponse(benchmarkList,courseType.getCourseType(), questionResponses);

			// saving response in DB
			quizResponse = quizResponseService.saveQuizResponse(quizResponse,quizRequest);
			
			logger.info("AuditResponse successfully created!!");
			
			response = new ResponseEntity<QuizResponse>(quizResponse, HttpStatus.OK);
		}
		else {
			logger.error("Failed to validate the JWT :: " + jwt);
			response = new ResponseEntity<>(quizResponse,HttpStatus.FORBIDDEN);
		}
		return response;
		
	}
}
