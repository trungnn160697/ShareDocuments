package com.rabiloo.sharedocument.dto.question;

public class QuestionRequest {
	private Integer idExam;
	private String contentQuestion;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String answerTrue;
	private String imageUrl;

	public QuestionRequest(Integer idExam, String contentQuestion, String answerA, String answerB, String answerC,
			String answerD, String answerTrue, String imageUrl) {
		super();
		this.idExam = idExam;
		this.contentQuestion = contentQuestion;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.answerTrue = answerTrue;
		this.imageUrl = imageUrl;
	}

	public Integer getIdExam() {
		return idExam;
	}

	public void setIdExam(Integer idExam) {
		this.idExam = idExam;
	}

	public String getContentQuestion() {
		return contentQuestion;
	}

	public void setContentQuestion(String contentQuestion) {
		this.contentQuestion = contentQuestion;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public String getAnswerTrue() {
		return answerTrue;
	}

	public void setAnswerTrue(String answerTrue) {
		this.answerTrue = answerTrue;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
