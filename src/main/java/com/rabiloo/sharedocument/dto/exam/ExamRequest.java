package com.rabiloo.sharedocument.dto.exam;

public class ExamRequest {
	private String name;
	private Integer subjectId;
	private Integer quantityQuestion;

	public ExamRequest(String name, Integer subjectId, Integer quantityQuestion) {
		super();
		this.name = name;
		this.subjectId = subjectId;
		this.quantityQuestion = quantityQuestion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
