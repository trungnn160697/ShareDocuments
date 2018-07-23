package com.rabiloo.sharedocument.validation;

public class ExamValidator {
	public static Boolean validateForm(String name, Integer subjectId, Integer quantityQuestion) {
		if (name == null || name.trim().equals("") || subjectId == null || subjectId == 0 || quantityQuestion == null) {
			return false;
		}
		return true;
	}
}
