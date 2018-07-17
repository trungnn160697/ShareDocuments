package com.rabiloo.sharedocument.dto.subject;

import java.util.List;

import com.rabiloo.sharedocument.util.MessageUtil;

public class SubjectResponse {

	private List<SubjectDto> subjectDtos;
	private SubjectDto subjectDto;
	private MessageUtil message;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private String sEcho;
	private List<SubjectDto> aaData;

	public SubjectResponse(MessageUtil message) {
		super();
		this.message = message;
	}

	public SubjectResponse() {
		super();
	}

	public List<SubjectDto> getSubjectDtos() {
		return subjectDtos;
	}

	public void setSubjectDtos(List<SubjectDto> subjectDtos) {
		this.subjectDtos = subjectDtos;
	}

	public SubjectDto getSubjectDto() {
		return subjectDto;
	}

	public void setSubjectDto(SubjectDto subjectDto) {
		this.subjectDto = subjectDto;
	}

	public MessageUtil getMessage() {
		return message;
	}

	public void setMessage(MessageUtil message) {
		this.message = message;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<SubjectDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<SubjectDto> aaData) {
		this.aaData = aaData;
	}

}
