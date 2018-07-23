package com.rabiloo.sharedocument.dto.document;

public class DocumentDto {
	private Integer id;
	private String name;
	private String description;
	private String image;
	private Double rate;
	private String date;
	private String nameOfSubject;
	private Integer numberOfDownload;
	private String document;
	private String usernameUser;
	private Integer idUser;
	private Integer subjectId;

	public DocumentDto() {
		super();
	}

	public DocumentDto(Integer id, String name, String description, String image, Double rate, String date,
			String nameOfSubject, Integer numberOfDownload, String document, String usernameUser, Integer idUser,
			Integer subjectId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.rate = rate;
		this.date = date;
		this.nameOfSubject = nameOfSubject;
		this.numberOfDownload = numberOfDownload;
		this.document = document;
		this.usernameUser = usernameUser;
		this.idUser = idUser;
		this.subjectId = subjectId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNameOfSubject() {
		return nameOfSubject;
	}

	public void setNameOfSubject(String nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	public Integer getNumberOfDownload() {
		return numberOfDownload;
	}

	public void setNumberOfDownload(Integer numberOfDownload) {
		this.numberOfDownload = numberOfDownload;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getUsernameUser() {
		return usernameUser;
	}

	public void setUsernameUser(String usernameUser) {
		this.usernameUser = usernameUser;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
