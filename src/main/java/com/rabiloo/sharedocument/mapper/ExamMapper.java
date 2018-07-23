package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.exam.ExamDto;
import com.rabiloo.sharedocument.dto.exam.ExamRequest;
import com.rabiloo.sharedocument.entity.Exam;
@Repository("examMapper")
public class ExamMapper {
	public ExamDto toDto(Exam exam) {
		return new ExamDto(exam.getId(), exam.getName(), exam.getSubject().getName(), exam.getSubject().getId(),
				exam.getQuantityQuestion(), exam.getDeleted());
	}

	public Exam toEntity(ExamRequest examRequest) {
		return new Exam(examRequest.getName(), examRequest.getQuantityQuestion(), false);
	}
}
