package com.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfpe.model.QuizBenchmark;

public interface QuizBenchmarkRepo extends JpaRepository<QuizBenchmark, Integer> {
	
}
