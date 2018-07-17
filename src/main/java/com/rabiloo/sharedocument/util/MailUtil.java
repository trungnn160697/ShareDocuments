package com.rabiloo.sharedocument.util;

import org.springframework.mail.SimpleMailMessage;

public class MailUtil {

	public static SimpleMailMessage sendMail(String toEmail, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(text);
		return message;
	}

}
