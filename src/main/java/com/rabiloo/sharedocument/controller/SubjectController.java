package com.rabiloo.sharedocument.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {

	@GetMapping("/subject")
	public String subject() {
		return "manager/subject";
	}

}
