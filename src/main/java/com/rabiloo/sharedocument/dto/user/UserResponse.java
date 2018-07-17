package com.rabiloo.sharedocument.dto.user;

import java.util.List;

import com.rabiloo.sharedocument.util.MessageUtil;

public class UserResponse {
	private List<UserDto> userDtos;
	private UserDto userDto;
	private MessageUtil message;
	private Long iTotalRecords;
	private Long iTotalDisplayRecords;
	private String sEcho;
	private List<UserDto> aaData;

	public UserResponse() {
		super();
	}

	public UserResponse(UserDto userDto) {
		super();
		this.userDto = userDto;
	}

	public UserResponse(MessageUtil message) {
		super();
		this.message = message;
	}

	public UserResponse(UserDto userDto, MessageUtil message) {
		super();
		this.userDto = userDto;
		this.message = message;
	}

	public UserResponse(List<UserDto> userDtos, MessageUtil message) {
		super();
		this.userDtos = userDtos;
		this.message = message;
	}

	public UserResponse(Long iTotalRecords, Long iTotalDisplayRecords, List<UserDto> aaData) {
		super();
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public List<UserDto> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

	public MessageUtil getMessage() {
		return message;
	}

	public void setMessage(MessageUtil message) {
		this.message = message;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<UserDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<UserDto> aaData) {
		this.aaData = aaData;
	}

}
