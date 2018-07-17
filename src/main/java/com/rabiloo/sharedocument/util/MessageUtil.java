package com.rabiloo.sharedocument.util;

public class MessageUtil {
	private String message;
	private Integer code;

	public MessageUtil() {
		super();
	}

	public MessageUtil(String message) {
		super();
		this.message = message;
	}

	public MessageUtil(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
