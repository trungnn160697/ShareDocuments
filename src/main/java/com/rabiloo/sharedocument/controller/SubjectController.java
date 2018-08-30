package com.rabiloo.sharedocument.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {

	@GetMapping("/subject")
	public String subject(HttpSession session) {
		session.setAttribute("active_menu",3);
		return "manager/subject";
	}

}
