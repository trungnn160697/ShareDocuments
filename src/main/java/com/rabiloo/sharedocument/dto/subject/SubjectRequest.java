package com.rabiloo.sharedocument.dto.subject;

public class SubjectRequest {
	private String name;
	private String code;
	private String description;

	public SubjectRequest(String name, String code, String description) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
	}

	public SubjectRequest() {
		super();
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

}
