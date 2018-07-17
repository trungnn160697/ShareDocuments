package com.rabiloo.sharedocument.validation;

public class SubjectValidation {
	public static Boolean validateForm(String name, String code, String description) {
		if (name == null || name.trim().equals("") || code == null || code.trim().equals("") || description == null
				|| description.trim().equals("")) {
			return false;
		}
		return true;
	}
}
