package com.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizSeverityApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizSeverityApplication.class, args);
		System.out.println("Quiz-Severity MS - Started.....");
	}

}
