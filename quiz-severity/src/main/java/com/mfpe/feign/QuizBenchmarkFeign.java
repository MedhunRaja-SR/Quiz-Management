package com.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.model.QuizBenchmark;

//url - specify the Benchmark module (application.properties)
@FeignClient(value = "quizbenchmark", url = "${ms.benchmark}")
public interface QuizBenchmarkFeign {
	
	@GetMapping("/QuizBenchmark")
	public List<QuizBenchmark> getQuizBenchmark(@RequestHeader("Authorization") String jwt); 
	
}
