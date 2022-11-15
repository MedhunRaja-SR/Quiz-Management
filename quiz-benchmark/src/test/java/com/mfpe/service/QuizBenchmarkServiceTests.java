package com.mfpe.service;


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
import com.mfpe.repository.QuizBenchmarkRepo;

@SpringBootTest
class QuizBenchmarkServiceTests {
	
	@Mock
	QuizBenchmarkRepo quizBenchmarkRepo;
	
	@InjectMocks
	QuizBenchmarkServiceImpl quizBenchmarkServiceImpl;
	
	@Test
	public void contextLoads() throws Exception {
		assertNotNull(quizBenchmarkServiceImpl);
	}
	
	@Test
	public void testGetQuizBenchmarkList() {
		List<QuizBenchmark> quizBenchmarkList = new ArrayList<>();
		quizBenchmarkList.add(new QuizBenchmark(1,"C"));
		quizBenchmarkList.add(new QuizBenchmark(2,"Java"));
		
		when(quizBenchmarkRepo.findAll()).thenReturn(quizBenchmarkList);
		
		assertEquals(quizBenchmarkList, quizBenchmarkServiceImpl.getQuizBenchmarkList());
	}
	
}
