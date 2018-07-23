package com.rabiloo.sharedocument.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "content_question")
	private String contentQuestion;

	@Column(name = "answer_a")
	private String answerA;

	@Column(name = "answer_b")
	private String answerB;

	@Column(name = "answer_c")
	private String answerC;

	@Column(name = "answer_d")
	private String answerD;

	@Column(name = "answer_true")
	private String answerTrue;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "deleted")
	private Boolean deleted;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;

	public Question(String contentQuestion, String answerA, String answerB, String answerC, String answerD,
			String answerTrue, String imageUrl, Boolean deleted) {
		super();
		this.contentQuestion = contentQuestion;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.answerTrue = answerTrue;
		this.imageUrl = imageUrl;
		this.deleted = deleted;
	}

	public Question() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
