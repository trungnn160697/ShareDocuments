package com.rabiloo.sharedocument.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rabiloo.sharedocument.util.Constants;

public class UserValidation {

	public static Boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static Boolean validatePhone(String phone) {
		if (phone.length() < 10 || phone.length() > 11)
			return false;
		Pattern pattern = Pattern.compile(Constants.PHONE_PATTERN);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public static Boolean validateForm(String username, String password, String fullName, String email, String phone) {
		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")
				|| fullName == null || fullName.trim().equals("") || email == null || email.trim().equals("")
				|| phone == null || phone.trim().equals("")) {
			return false;
		}
		return true;
	}

	public static Boolean validateForm(String fullName, String email, String phone) {
		if (fullName == null || fullName.trim().equals("") || email == null || email.trim().equals("") || phone == null
				|| phone.trim().equals("")) {
			return false;
		}
		return true;
	}

	public static Boolean validatePassword(String password) {
		if (password.length() < 6) {
			return false;
		}
		return true;
	}

}
