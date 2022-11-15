package com.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="quiz_benchmark")
public class QuizBenchmark {
	
	@Id
	@Column(name="benchmark_id")
	@GeneratedValue
	private int benchmarkId;
	
	@Column(name="course_type")
	private String courseType;
	
}
