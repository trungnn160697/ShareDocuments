package com.rabiloo.sharedocument.dto.exam;

import java.util.List;

import com.rabiloo.sharedocument.util.MessageUtil;

public class ExamResponse {

	private List<ExamDto> examDtos;
	private ExamDto examtDto;
	private MessageUtil message;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private String sEcho;
	private List<ExamDto> aaData;

	public ExamResponse(MessageUtil message) {
		super();
		this.message = message;
	}

	public ExamResponse() {
		super();
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

	public List<ExamDto> getExamDtos() {
		return examDtos;
	}

	public void setExamDtos(List<ExamDto> examDtos) {
		this.examDtos = examDtos;
	}

	public ExamDto getExamtDto() {
		return examtDto;
	}

	public void setExamtDto(ExamDto examtDto) {
		this.examtDto = examtDto;
	}

	public List<ExamDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<ExamDto> aaData) {
		this.aaData = aaData;
	}

}
