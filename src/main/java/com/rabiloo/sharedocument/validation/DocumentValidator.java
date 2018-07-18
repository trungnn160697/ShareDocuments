package com.rabiloo.sharedocument.validation;

public class DocumentValidator {

	public static boolean validateForm(String name, Integer idSubject, String description) {
		if (name == null || name.trim().equals("") || idSubject == null || idSubject == 0 || description == null
				|| description.trim().equals(""))
			return false;
		return true;
	}
}
