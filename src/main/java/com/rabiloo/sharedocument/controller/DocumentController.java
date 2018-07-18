package com.rabiloo.sharedocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rabiloo.sharedocument.service.SubjectService;

@Controller
public class DocumentController {
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/document")
	public String document(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		return "manager/document";
	}

}
