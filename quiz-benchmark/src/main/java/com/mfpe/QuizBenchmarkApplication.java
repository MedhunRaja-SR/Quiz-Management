package com.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizBenchmarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizBenchmarkApplication.class, args);
		System.out.println("Quiz-BenchMark MS - Started.....");
	}

}
