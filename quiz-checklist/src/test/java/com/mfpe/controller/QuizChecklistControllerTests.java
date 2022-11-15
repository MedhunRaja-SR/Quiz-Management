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

import com.mfpe.model.CourseType;
import com.mfpe.model.Question;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuestionService;

@SpringBootTest
public class QuizChecklistControllerTests {
	
	@Mock
	QuestionService questionService;
	
	@Mock
	AuthorizationService authorizationService;
	
	@InjectMocks
	QuizChecklistController controller;
	
	@Test
	public void contextLoads() {
		assertNotNull(controller);
	}
	
	@Test
	public void testHealthCheck() {
		assertEquals("Quiz Checklist Microservice is Active", controller.healthCheck());
	}
	
	@Test
	public void testQuizChecklistQuestions() {
		List<Question> questions =  new ArrayList<Question>();
		questions.add(new Question(1,"question","courseType","option1","option2","option3","option4","response"));
		
		when(authorizationService.validateJwt("jwt")).thenReturn(true);
		
		CourseType courseType = new CourseType("courseType");
		when(questionService.getQuestionsByCourseType(courseType)).thenReturn(questions);
		
		assertEquals(questions, controller.quizCheckListQuestions("jwt", courseType));		
	}
	
}
