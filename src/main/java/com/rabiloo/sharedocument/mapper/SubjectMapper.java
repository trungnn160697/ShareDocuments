package com.rabiloo.sharedocument.mapper;

import org.springframework.stereotype.Repository;

import com.rabiloo.sharedocument.dto.subject.SubjectDto;
import com.rabiloo.sharedocument.dto.subject.SubjectRequest;
import com.rabiloo.sharedocument.entity.Subject;

@Repository("subjectMapper")
public class SubjectMapper {
	public SubjectDto toDto(Subject subject) {
		return new SubjectDto(subject.getId(), subject.getName(), subject.getCode(), subject.getDescription(),subject.getDeleted());
	}

	public Subject toEntity(SubjectDto subjectDto) {
		return new Subject(subjectDto.getId(), subjectDto.getName(), subjectDto.getCode(), subjectDto.getDescription(),subjectDto.getDeleted());
	}
	
	public Subject toEntity(SubjectRequest subjectRequest) {
		return new Subject(subjectRequest.getName(), subjectRequest.getCode(), subjectRequest.getDescription());
	}
}
