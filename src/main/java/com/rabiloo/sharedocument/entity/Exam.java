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
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "quantity_question")
	private Integer quantityQuestion;

	@Column(name = "deleted")
	private Boolean deleted;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	public Exam() {
		super();
	}

	public Exam(String name, Integer quantityQuestion, Subject subject, Boolean deleted) {
		super();
		this.name = name;
		this.quantityQuestion = quantityQuestion;
		this.subject = subject;
		this.deleted = deleted;
	}

	public Exam(String name, Integer quantityQuestion, Boolean deleted) {
		super();
		this.name = name;
		this.quantityQuestion = quantityQuestion;
		this.deleted = deleted;
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

	public Integer getQuantityQuestion() {
		return quantityQuestion;
	}

	public void setQuantityQuestion(Integer quantityQuestion) {
		this.quantityQuestion = quantityQuestion;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
