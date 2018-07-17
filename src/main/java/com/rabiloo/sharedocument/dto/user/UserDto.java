package com.rabiloo.sharedocument.dto.user;

public class UserDto {
	private Integer id;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private String image;
	private String roleName;

	public UserDto() {
		super();
	}

	public UserDto(Integer id, String username, String fullName, String email, String phone, String image,
			String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.roleName = roleName;
	}

	public UserDto(Integer id, String username, String password, String fullName, String email, String phone,
			String image, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
