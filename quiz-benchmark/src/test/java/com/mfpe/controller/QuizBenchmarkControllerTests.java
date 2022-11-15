package com.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.QuizBenchmark;
import com.mfpe.service.QuizBenchmarkService;
import com.mfpe.service.AuthorizationService;

@SpringBootTest
class QuizBenchmarkControllerTests {
	
	@Mock
	QuizBenchmarkService quizBenchmarkService;
	
	@Mock
	AuthorizationService authorizationService;
	
	@InjectMocks
	QuizBenchmarkController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertNotNull(controller);
	}
	
	@Test
	public void testHealthCheck() {
		assertEquals("Audit Benchmark Microservice is Active",controller.healthCheck());
	}
	
	@Test
	public void testGetQuizBenchmark() {
		List<QuizBenchmark> quizBenchmarkList = new ArrayList<>();
		quizBenchmarkList.add(new QuizBenchmark(1,"C"));
		quizBenchmarkList.add(new QuizBenchmark(2,"Java"));
		
		when(authorizationService.validateJwt("jwt")).thenReturn(true);
		
		when(quizBenchmarkService.getQuizBenchmarkList()).thenReturn(quizBenchmarkList);
		
		assertEquals(quizBenchmarkList, controller.getQuizBenchmark("jwt"));
	}
	
}
