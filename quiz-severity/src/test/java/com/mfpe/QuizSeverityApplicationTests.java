package com.mfpe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class QuizSeverityApplicationTests {	
	
	@Autowired
	QuizSeverityApplication quizSeverityApplication;
	
	@Test
	public void contextLoads(){
		assertNotNull(quizSeverityApplication);
	}

}
