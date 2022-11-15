package com.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mfpe.model.CourseType;
import com.mfpe.model.QuizQuestion;

//url - specify the checklist module (application.properties)
@FeignClient(url = "${ms.checklist}", name="quizChecklist")
public interface QuizCheckListFeign {
	
	@RequestMapping(value = "/QuizCheckListQuestions", method = RequestMethod.POST )
	public List<QuizQuestion> quizCheckListQuestions(@RequestHeader("Authorization") String jwt, @RequestBody CourseType courseType);
	
}
