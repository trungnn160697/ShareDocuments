package com.rabiloo.sharedocument.dto.exam;

import java.util.List;

import com.rabiloo.sharedocument.dto.question.QuestionDto;

public class ExamDto {
	private Integer id;
	private String name;
	private String nameSubject;
	private Integer subjectId;
	private Integer quantityQuestion;
	private Boolean deleted;
	private List<QuestionDto> questionDtos;

	public ExamDto(Integer id, String name, String nameSubject, Integer subjectId, Integer quantityQuestion,
			Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.nameSubject = nameSubject;
		this.subjectId = subjectId;
		this.quantityQuestion = quantityQuestion;
		this.deleted = deleted;
	}

	public ExamDto(Integer id, String name, String nameSubject, Integer subjectId, Integer quantityQuestion,
			Boolean deleted, List<QuestionDto> questionDtos) {
		super();
		this.id = id;
		this.name = name;
		this.nameSubject = nameSubject;
		this.subjectId = subjectId;
		this.quantityQuestion = quantityQuestion;
		this.deleted = deleted;
		this.questionDtos = questionDtos;
	}

	public ExamDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getQuantityQuestion() {
		return quantityQuestion;
	}

	public void setQuantityQuestion(Integer quantityQuestion) {
		this.quantityQuestion = quantityQuestion;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<QuestionDto> getQuestionDtos() {
		return questionDtos;
	}

	public void setQuestionDtos(List<QuestionDto> questionDtos) {
		this.questionDtos = questionDtos;
	}

}
