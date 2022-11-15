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

import com.mfpe.model.CourseType;
import com.mfpe.model.Question;
import com.mfpe.repository.QuestionRepo;

@SpringBootTest
public class QuestionServiceTests {
	
	@Mock
	QuestionRepo questionRepo;
	
	@InjectMocks
	QuestionServiceImpl questionService;
	
	@Test
	void contextLoads() {
		assertNotNull(questionService);
	}
	
	@Test
	void testGetQuestionsByCourseType() {
		List<Question> questions = new ArrayList<>();
		questions.add(new Question(1,"question","courseType","option1","option2","option3","option4","response"));
		CourseType courseType = new CourseType("courseType");
		
		when(questionRepo.getQuestionsByCourseType("courseType")).thenReturn(questions);
		assertEquals(questions, questionService.getQuestionsByCourseType(courseType));
	}
	
}
