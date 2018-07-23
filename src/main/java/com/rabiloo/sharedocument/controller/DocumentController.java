package com.rabiloo.sharedocument.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabiloo.sharedocument.service.SubjectService;
import com.rabiloo.sharedocument.service.UserService;

@Controller
public class DocumentController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UserService userService;

	@GetMapping("/document")
	public String document(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		return "manager/document";
	}

	@GetMapping("/document/user-document/{id}")
	public String userDocument(@PathVariable("id") Integer id, HttpSession session, Model model) {
		session.setAttribute("userOfId", id);
		model.addAttribute("user", userService.findById(id));
		return "manager/list-document-user";
	}

	@GetMapping("/document/subject-document/{id}")
	public String subjcetDocument(@PathVariable("id") Integer id, HttpSession session, Model model) {
		session.setAttribute("subjectOfId", id);
		model.addAttribute("subject", subjectService.findById(id));
		return "manager/list-document-subject";
	}

}
