package com.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.feign.QuizBenchmarkFeign;
import com.mfpe.model.QuizBenchmark;
import com.mfpe.model.QuizDetail;
import com.mfpe.model.QuizQuestion;
import com.mfpe.model.QuizRequest;
import com.mfpe.model.QuizResponse;
import com.mfpe.service.QuizResponseService;
import com.mfpe.service.AuthorizationService;

@SpringBootTest
class QuizSeverityControllerTests {
	
	@Mock
	QuizResponseService quizResponseService;
	
	@Mock
	AuthorizationService authorizationService;
	
	@Mock
	QuizBenchmarkFeign quizBenchmarkFeign;
	
	@InjectMocks
	QuizSeverityController severityController;
	
		
	@Test
	public void testSeverityController() throws Exception {
		assertThat(severityController).isNotNull();
	}
	
	@Test
	public void testQuizSeverityStatus() {
		
		Date date = new Date();
		
		List<QuizBenchmark> benchmarkList = new ArrayList<>();
		benchmarkList.add(new QuizBenchmark(1,"Internal"));
		
		List<QuizQuestion> questionResponseList = new ArrayList<>();
		questionResponseList.add(new QuizQuestion(1,"question","C","/","//","/*","*/","//"));
		
		QuizRequest quizRequest = new QuizRequest("ManagerName",new QuizDetail("Internal",date,questionResponseList));
		
		QuizResponse quizResponse = new QuizResponse(1,1, "ManagerName","courseType", new Date(),"ProjectExecutionStatus","RemedialActionDuration");
		
		when(authorizationService.validateJwt("jwt")).thenReturn(true);
		
		when(quizBenchmarkFeign.getQuizBenchmark("jwt")).thenReturn(benchmarkList);
		
		when(quizResponseService.getQuizResponse(benchmarkList,"Internal", questionResponseList)).thenReturn(quizResponse);
		
		when(quizResponseService.saveQuizResponse(quizResponse,quizRequest))
			.thenReturn(quizResponse);
		
		
		ResponseEntity<QuizResponse> expectedResponse = new ResponseEntity<QuizResponse>(quizResponse, HttpStatus.OK);
		
		assertEquals(expectedResponse,severityController.quizSeverity("jwt",quizRequest));
	}
	
	
	@Test
	public void testAuditHealthCheck() {
		assertEquals(severityController.healthCheck(),"Quiz Severity Microservice is Active");
	}
	
	
	
}
