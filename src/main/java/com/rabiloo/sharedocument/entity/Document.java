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
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "number_of_download")
	private Integer numberOfDownload;

	@Column(name = "image")
	private String image;

	@Column(name = "date")
	private String date;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "link_document")
	private String linkDocument;

	@Column(name = "deleted")
	private Boolean deleted;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Document(String name, String description, String image, String date, String linkDocument) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.date = date;
		this.linkDocument = linkDocument;
	}

	public Document() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfDownload() {
		return numberOfDownload;
	}

	public void setNumberOfDownload(Integer numberOfDownload) {
		this.numberOfDownload = numberOfDownload;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getLinkDocument() {
		return linkDocument;
	}

	public void setLinkDocument(String linkDocument) {
		this.linkDocument = linkDocument;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
