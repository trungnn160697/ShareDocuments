package com.rabiloo.sharedocument.util;

public class QuestionValidator {
	public static boolean validateForm(String content, String answerA, String answerB, String answerC, String answerD,
			String answerTrue) {
		if (content == null || content.trim().equals("") || answerA == null || answerA.trim().equals("")
				|| answerB == null || answerB.trim().equals("") || answerC == null || answerC.trim().equals("")
				|| answerD == null || answerD.trim().equals("") || answerTrue == null || answerA.trim().equals("0")) {
			return false;
		}
		return true;
	}
}
