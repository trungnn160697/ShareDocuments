package com.rabiloo.sharedocument.dto.document;

public class DocumentRequest {
	private Integer id;
	private String name;
	private String description;
	private String image;
	private String date;
	private Integer subjectId;
	private String document;
	private Integer idUser;

	public DocumentRequest() {
		super();
	}

	public DocumentRequest(Integer id, String name, String description, String image, String date, Integer subjectId,
			String document, Integer idUser) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.date = date;
		this.subjectId = subjectId;
		this.document = document;
		this.idUser = idUser;
	}

	public DocumentRequest(String name, String description, String image, String date, Integer subjectId,
			String document) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.date = date;
		this.subjectId = subjectId;
		this.document = document;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}
