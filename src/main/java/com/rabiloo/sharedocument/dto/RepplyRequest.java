package com.rabiloo.sharedocument.dto;

public class RepplyRequest {
	private String email;
	private String title;
	private String content;

	public RepplyRequest(String email, String title, String content) {
		super();
		this.email = email;
		this.title = title;
		this.content = content;
	}

	public RepplyRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
