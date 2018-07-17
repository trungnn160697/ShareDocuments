package com.rabiloo.sharedocument.dto.subject;

public class SubjectDto {
	private Integer id;
	private String name;
	private String code;
	private String description;
	private Boolean deleted;

	public SubjectDto(Integer id, String name, String code, String description, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.deleted = deleted;
	}

	public SubjectDto() {
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

}
