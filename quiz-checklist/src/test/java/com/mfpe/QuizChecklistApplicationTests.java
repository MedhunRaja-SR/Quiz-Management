package com.mfpe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuizChecklistApplicationTests {

	@Autowired
	QuizChecklistApplication quizChecklistApplication;
	
	@Test
	void contextLoads() {
		assertNotNull(quizChecklistApplication);
	}

}
