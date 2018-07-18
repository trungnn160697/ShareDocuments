package com.rabiloo.sharedocument.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "deleted")
	private Boolean deleted;

	@OneToMany(mappedBy = "subject")
	private List<Document> listDocument;

	public Subject() {
		super();
	}

	public Subject(Integer id, String name, String code, String description, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.deleted = deleted;
	}

	public Subject(String name, String code, String description) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Document> getListDocument() {
		return listDocument;
	}

	public void setListDocument(List<Document> listDocument) {
		this.listDocument = listDocument;
	}

}
