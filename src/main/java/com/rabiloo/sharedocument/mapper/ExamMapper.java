package com.rabiloo.sharedocument.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.exam.ExamDto;
import com.rabiloo.sharedocument.dto.exam.ExamRequest;
import com.rabiloo.sharedocument.dto.question.QuestionDto;
import com.rabiloo.sharedocument.entity.Exam;
import com.rabiloo.sharedocument.entity.Question;

@Repository("examMapper")

public class ExamMapper {
	@Autowired
	private QuestionMapper questionMapper;

	public ExamDto toDto(Exam exam) {
		List<QuestionDto> questionDtos = new ArrayList<>();
		for (Question question : exam.getQuestions()) {
			QuestionDto questionDto = questionMapper.toDto(question);
			questionDtos.add(questionDto);
		}

		return new ExamDto(exam.getId(), exam.getName(), exam.getSubject().getName(), exam.getSubject().getId(),
				exam.getQuantityQuestion(), exam.getDeleted(), questionDtos);
	}

	public Exam toEntity(ExamRequest examRequest) {
		return new Exam(examRequest.getName(), examRequest.getQuantityQuestion(), false);
	}
}
