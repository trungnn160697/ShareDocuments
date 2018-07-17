package com.rabiloo.sharedocument.dto.user;

public class UserRequest {
	private Long id;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private String image;
	private Integer deleted;

	public UserRequest(String username, String password, String fullName, String email, String phone, String image) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.image = image;
	}

	public UserRequest(Long id, String username, String password, String fullName, String email, String phone,
			String image) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.image = image;
	}

	public UserRequest(String fullName, String email, String phone, String image) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
