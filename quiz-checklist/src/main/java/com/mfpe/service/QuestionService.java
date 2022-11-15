package com.mfpe.service;

import java.util.List;

import com.mfpe.model.CourseType;
import com.mfpe.model.Question;

public interface QuestionService {

	public List<Question> getQuestionsByCourseType(CourseType courseType);
}
