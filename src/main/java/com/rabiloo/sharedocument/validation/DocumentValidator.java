package com.rabiloo.sharedocument.validation;

import org.springframework.web.multipart.MultipartFile;

public class DocumentValidator {

	public static boolean validateForm(String name, Integer idSubject, String description,MultipartFile file) {
		if (name == null || name.trim().equals("") || idSubject == null || idSubject == 0 || description == null
				|| description.trim().equals("")||file==null)
			return false;
		return true;
	}
}
